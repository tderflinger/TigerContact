package org.tigercontact.contacts.db

import groovy.util.logging.Log4j

import javax.validation.ConstraintViolationException

import org.hibernate.HibernateException
import org.hibernate.Query
import org.hibernate.Session
import org.hibernate.Transaction
import org.tigercontact.contacts.Contact
import org.tigercontact.contacts.util.HibernateUtil

/**
 * Does the loading and saving of the contact class using Hibernate.
 * 
 * 
 * @author tderflinger
 *
 */
@Log4j
class HibernateContact {
	
	def Contact readContact(String id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession()
		session.beginTransaction()
		Query query = session.createQuery("from Contact where id=:id")
		query.setString("id", id)
		Contact contact = query.uniqueResult()
		session.getTransaction().commit()
		return contact
	}

	def saveContact(Contact contact) throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession()

		Transaction tx = null;

		try {
			tx = session.beginTransaction()
			session.save(contact)
			tx.commit()
		} catch (HibernateException e) {
			log.error "Exception has occured: "+e.getMessage()
			if (tx != null) {
				tx.rollback()
			}
			throw e;
		} catch (ConstraintViolationException e) {
			log.error "ConstraintViolationException has occured: "+e.getMessage()
			
			if (tx != null) {
				tx.rollback()
			}
			throw e;
		}
	}

	
}
