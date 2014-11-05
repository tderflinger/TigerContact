TigerContact
============

Use Case
--------

Did you ever need to implement a contact form for a website? If your answer is yes, then the TigerContact micro service is for you. It is implemented as a RESTful web service and collects the form data and stores it in a database.
The sending of an email upon submission is in preparation. 


Technical Details
-----------------

TigerContact is a micro service for receiving contact information in a website. It is a RESTful service written in Groovy and Jersey 2.11. It uses Hibernate and an embedded database (H2) for storing the contact information. Furthermore, Guice is included for dependency injection.

Spock is used for testing. Gradle is the build tool.

To start the RESTful web service use the following gradle command:

gradle clean jettyRun


