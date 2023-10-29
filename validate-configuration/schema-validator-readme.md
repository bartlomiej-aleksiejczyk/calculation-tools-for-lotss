# Computation tools - 00
## Schema Validator
The Schema Validator app  validates global `configuration.xml` file aganist `schema.xsd`.
## How to use
### Prequisites
* Java JDK 21 installed.
* The File `configuration.xml` should be available in the main folder.
* The File `schema.xsd` should be available in the `validate-configuration` folder.
### How to run with maven wrapper
* Navigate to the program directory by executing the following in the command console: ```cd validate-configuration```
* Compile and run using maven with: ```./mvnw clean install exec:java -q```
### How to build a jar (fatjar) for the project [optional]
* Navigate to the program directory by executing the following in the command console: ```cd validate-configuration```
* Compile the project by executing the following in the command console: ```./mvnw clean package```
* The jar file will be available in `target` directory with `jar-with-dependencies` postfix and `.jar` extension.
* Move the jar file to the parent directory.
### How to run the jar file [optional]
* Navigate to the program directory by executing the following in the command console: ```cd validate-configuration```
* Run using JDK with: `java -jar your_filename.jar`.