package org.tigercontact.contacts

import java.sql.Timestamp


class ParseContacts {


	/**
	 * Parses an XML stream and maps the XML elements to the Contact class.
	 * 
	 * @param is
	 * @return
	 */
	def Contact parseXmlStream(InputStream is) {
		def xmlContact = new XmlParser().parse(is)

		Contact contact = new Contact()
		contact.email = xmlContact.email.text()
		contact.name = xmlContact.name.text()
		contact.phone = xmlContact.phone.text()
		contact.moreInfo = xmlContact.moreInfo.text().toBoolean()
		contact.message = xmlContact.message.text()

		contact.timeStamp = new Timestamp(new Date().getTime())

		return contact
	}
}

