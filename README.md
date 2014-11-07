TigerContact
============

Use Case
--------

Did you ever need to implement a contact form for a website? If your answer is yes, then the TigerContact micro service is for you. It is implemented as a RESTful web service and collects the form data and stores it in a database.
The sending of an email upon submission is in preparation. 


Technical Details
-----------------

TigerContact is a micro service for receiving contact information in a website. It is a RESTful service written in Groovy and Jersey 2.11. It uses Hibernate and an embedded database (H2) for storing the contact information. Furthermore, Guice is included for dependency injection.

Spock is used for testing. Gradle 2.1 is the build tool.

To start the RESTful web service use the following gradle command:

gradle clean jettyRun

Future Development
------------------

I would like to encourage you to develop similar RESTful web services in different languages. So in the future one is able to see the different strengths and weaknesses of each implementation language.

For example, try to implement a RESTful micro service in Ruby, Python, JavaScript, etc.

Feedback
--------
For any kind of feedback I am deeply grateful. Please send en email to: tigerontact@batino.eu
Thanks!
