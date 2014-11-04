package org.tigercontact.contacts

import groovy.xml.MarkupBuilder


/**
 * Uses the Groovy MarkupBuilder to create the GET response.
 * 
 * @author tderflinger
 *
 */
class ContactResponseBuilder {

	def String xmlResponse(Contact contact) {
		def writer = new StringWriter()
		def builder = new MarkupBuilder(writer)
		builder.response {
			identifier(contact.id)
			time(contact.timeStamp)
			name(contact.name)
			email(contact.email)
			phone(contact.phone)
			message(contact.message)
			moreInfo(contact.moreInfo)
		}
		
		return writer.toString()
	}
	
}
