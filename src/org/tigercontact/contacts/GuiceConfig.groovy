package org.tigercontact.contacts

import org.tigercontact.contacts.db.HibernateContact

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.servlet.GuiceServletContextListener
import com.google.inject.servlet.ServletModule

class GuiceConfig extends GuiceServletContextListener {

	public static Injector injector;

	@Override
	protected Injector getInjector() {
		injector = Guice.createInjector(new ServletModule() {
					// Configure your IOC
					@Override
					protected void configureServlets() {
						bind(HibernateContact.class)
						bind(ContactResponseBuilder.class)
						bind(ParseContacts.class)
					}
				});

		return injector;
	}

}
