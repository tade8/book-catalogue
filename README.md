# ********************Book catalogue application********************

## Installation

Run the following command in a terminal

Windows

    mvn.cmd clean install

Linux / macOS

    ./mvnw clean install


### **Build the book management application**

Open a terminal and navigate to the book management service module

    cd bookmanagementservice/application 

Windows

    mvn.cmd clean install

Linux / macOS

    ./mvnw clean install

### **Run the jar**
    
    java -jar target/application-0.0.1-SNAPSHOT.jar


Open a new terminal and navigate to the webservice module:

### Build the web service application
    
    cd webservice 

Windows

    mvn.cmd clean install

Linux / macOS

    ./mvnw clean install

### **Run the jar**

    java -jar target/webservice-0.0.1.jar

Open your browser to access the web service page at this address:
[http://localhost:8081/books](http://localhost:8081/books/create)