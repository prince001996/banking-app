### Objective
to build an internal API for a financial institution.

### Brief
While modern banks have evolved to serve a plethora of functions, at their core, banks must provide certain basic features. Today, task is to build the basic HTTP API for one of those banks! Imagine you are designing a backend API for bank employees. It could ultimately be consumed by multiple frontends (web, iOS, Android etc).

### Features
- There should be API routes that allow them to:
  - Create a new bank account for a customer, with an initial deposit amount. A
    single customer may have multiple bank accounts.
     - done in two steps :
       - add/create a customer
       - assignAccount to Customer
  - Transfer amounts between any two accounts, including those owned by
    different customers.
  - Retrieve balances for a given account.
  - Retrieve transfer history for a given account.
  
### Steps to Run the application :
* clone the project from http://atlys-stlisj@git.codesubmit.io/atlys/python-any-framework-osefhq
* run the project
  * Run using IDE
    * open the project in the IDE as a maven project
    * right click on pom.xml and click on "maven -> reload project" or "reload maven project"
    * can be directly run using the BankApplication.main() and it starts the application on port 7050

  * Run using Command Line
    * open terminal
    * go to project directory
    * run "mvn clean" 
    * run "mvn package" (it craetes a jar)
    * run the command "java -jar target/<project jar>" (replace <project jar> with the name of the .jar file created in target directory inside the project directory)

* the project will run on port 7050 which can be configured in application.properties
* Now open postman and try out the apis
* If any issues contact me at "pkprince48@gmail.com"