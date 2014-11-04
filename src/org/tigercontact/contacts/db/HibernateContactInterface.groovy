package org.tigercontact.contacts.db

import org.tigercontact.contacts.Contact


public interface HibernateContactInterface {

	def Contact readContact(String id)
	
	def saveContact(Contact contact) throws Exception
	
}
