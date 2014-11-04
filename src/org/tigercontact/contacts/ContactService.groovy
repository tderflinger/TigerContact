package org.tigercontact.contacts

import groovy.util.logging.Log4j
import groovy.xml.MarkupBuilder

import javax.inject.Inject
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import org.hibernate.HibernateException
import org.tigercontact.contacts.db.HibernateContact

import com.google.inject.servlet.RequestScoped


@RequestScoped
@Path("/contact")
@Log4j
public class ContactService {

	@Inject
	HibernateContact hibernateContact

	@Inject
	ContactResponseBuilder responseBuilder

	@Inject
	ParseContacts parseContacts

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getContact(@PathParam("param") String id) {
		log.info "GET Parameter: "+id

		Contact contact = hibernateContact.readContact(id)

		if (contact != null) {
			return Response.status(200).entity(responseBuilder.xmlResponse(contact)).build();
		} else {
			return Response.status(404).build()  // not found
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response postContact(InputStream is) {
		log.info "POST Contact"

		Contact contact = parseContacts.parseXmlStream(is)

		try {
			hibernateContact.saveContact(contact)
		} catch (HibernateException e) {
			log.error("Hibernate  exception: "+e.getMessage())
			return Response.status(400).entity("<errors><error><message>"+e.getMessage()+"</message><property>general</property></error></errors>").build()
		} catch (ConstraintViolationException e) {
			def writer = new StringWriter()
			def builder = new MarkupBuilder(writer)

			builder.errors {
				Set<ConstraintViolation> constraintViolations = e.getConstraintViolations()
				for (ConstraintViolation viol : constraintViolations) {
					error {
						log.info "Constraint message: "+viol.message
						log.info "Property path: "+viol.propertyPath
						message(viol.message)
						property(viol.propertyPath.toString().capitalize())
					}
				}
			}
			return Response.status(400).entity(writer.toString()).build()
		}

		log.info "POST id: " + contact.id

		return Response.created(URI.create("/contacts/"+contact.id)).build()
	}
}