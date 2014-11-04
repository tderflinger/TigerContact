package org.tigercontact.contacts.tests

import javax.validation.ConstraintViolationException

import org.hibernate.HibernateException
import org.tigercontact.contacts.Contact
import org.tigercontact.contacts.db.HibernateContact

import spock.lang.Specification



/**
 * Do not run when TigerContact runs at the same time.
 * 
 * @author tderflinger
 *
 */
class HibernateContactSpec extends Specification {

	def "Save correct contact object to database with Hibernate."() {
		setup:
		def contact = new Contact()
		contact.email = "to8@hotmail.com"
		contact.name = "testname"
		contact.id = 10
		contact.message = "just a message"
		contact.moreInfo = true
		contact.phone = "089888"

		when:
		HibernateContact hiberContact = new HibernateContact()

		then: "save via Hibernate without exception"
		try {
			hiberContact.saveContact(contact)
		} catch (HibernateException e) {
			assert false
		}
	}


	def "Save Contact object with malformed email address to database with Hibernate."() {
		setup:
		def contact = new Contact()
		contact.email = "to8hotmail.com"
		contact.name = "testname"
		contact.id = 10
		contact.message = "just a message"
		contact.moreInfo = true
		contact.phone = "089888"

		when:
		HibernateContact hiberContact = new HibernateContact()

		then: "save via Hibernate without exception"
		try {
			hiberContact.saveContact(contact)
			assert false
		} catch (ConstraintViolationException e) {
			println "Constraint violation: "+e.getConstraintViolations()
		}
	}
}
