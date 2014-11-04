package org.tigercontact.contacts.util

import groovy.util.logging.Log4j

import org.hibernate.HibernateException
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import org.hibernate.service.ServiceRegistry


@Log4j
class HibernateUtil {

	private static final SessionFactory SESSIONFACTORY = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					configuration.getProperties()).build();
			return configuration.buildSessionFactory(serviceRegistry);
		}
		catch (HibernateException ex) {
			log.error "Initial SessionFactory creation failed." + ex
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return SESSIONFACTORY;
	}
}
