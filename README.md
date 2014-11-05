TigerContact
============

TigerContact is a micro service for receiving contact information in a website. It is a RESTful service written in Groovy and Jersey 2.11. It uses Hibernate and an embedded database (H2) for storing the contact information. Furthermore, Guice is included for dependency injection.

Spock is used for testing. Gradle is the build tool.

To start the RESTful web service use the following gradle command:

gradle clean jettyRun


