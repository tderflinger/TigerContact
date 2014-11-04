package org.tigercontact.contacts.tests

import javax.xml.XMLConstants
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

import org.tigercontact.contacts.Contact
import org.tigercontact.contacts.ContactResponseBuilder

import spock.lang.Specification



class ContactResponseBuilderSpec extends Specification {

	def "Build XML and see if I can parse it."() {
		setup:
		def responseBuilder = new ContactResponseBuilder()
		def contact = new Contact()
		contact.email = "to8@hotmail.com"
		contact.name = "testname"
		contact.id = 10
		contact.message = "just a message"
		contact.moreInfo = true
		contact.phone = "089888"
		
		when:
		String xmlString = responseBuilder.xmlResponse(contact)
		
		then: "validate to schema and parse xml"
		def factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
		def schema = factory.newSchema(new File("schema/contact.xsd")) 
		def validator = schema.newValidator()
		validator.validate(new StreamSource(new StringReader(xmlString)))
		
		def xmlContact = new XmlParser().parse(new ByteArrayInputStream(xmlString.getBytes( 'UTF-8' )))
		xmlContact.email.text() == "to8@hotmail.com"
		xmlContact.name.text() == "testname"
		xmlContact.identifier.text().toInteger() == 10
		xmlContact.phone.text() == "089888"
		xmlContact.message.text() == "just a message"
		xmlContact.moreInfo.text().toBoolean() == true
	}
	
}
