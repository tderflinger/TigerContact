package org.tigercontact.contacts.tests

import groovy.xml.MarkupBuilder

import org.tigercontact.contacts.Contact
import org.tigercontact.contacts.ParseContacts

import spock.lang.Specification


class ParseContactsSpec extends Specification {

	def "Parse contact xml stream"() {
		setup: "create a test xml markup string"
		def writer = new StringWriter()
		def builder = new MarkupBuilder(writer)
		builder.contact {
			name("TestName")
			email("test@email.com")
			phone("1234")
			message("TestMessage")
			moreInfo("0")
		}

		when: "the xml string is parsed as a stream and the contact object returned"
		Contact contact = new ParseContacts().parseXmlStream(new ByteArrayInputStream( writer.toString().getBytes( 'UTF-8' ) ))
		
		then: "the attributes of the return contact object should be equal to the elements in the xml"
		contact.name == "TestName"
		contact.email == "test@email.com"
		contact.phone == "1234"
		contact.message == "TestMessage"
		contact.moreInfo == false
	}
	
	def "Parse contact xml stream with moreInfo true"() {
		setup:
		def writer = new StringWriter()
		def builder = new MarkupBuilder(writer)
		builder.contact {
			name("TestName")
			email("test@email.com")
			phone("1234")
			message("TestMessage")
			moreInfo("1")
		}

		when:
		Contact contact = new ParseContacts().parseXmlStream(new ByteArrayInputStream( writer.toString().getBytes( 'UTF-8' ) ))
		
		then:
		contact.name == "TestName"
		contact.email == "test@email.com"
		contact.phone == "1234"
		contact.message == "TestMessage"
		contact.moreInfo == true
	}

	
	def "Parse contact xml stream with wrong email address"() {
		setup: "create a test xml markup string"
		def writer = new StringWriter()
		def builder = new MarkupBuilder(writer)
		builder.contact {
			name("TestName")
			email("testemail.com")
			phone("1234")
			message("TestMessage")
			moreInfo("0")
		}

		when: "the xml string is parsed as a stream and the contact object returned"
		Contact contact = new ParseContacts().parseXmlStream(new ByteArrayInputStream( writer.toString().getBytes( 'UTF-8' ) ))
		
		then: "the attributes of the return contact object should be equal to the elements in the xml"
		contact.name == "TestName"
		contact.email == "testemail.com"
		contact.phone == "1234"
		contact.message == "TestMessage"
		contact.moreInfo == false
	}

	
}
