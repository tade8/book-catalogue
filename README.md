# ********************Book catalogue application********************

### **Build and run the book management application**

Open a terminal and navigate to the library module

    cd bookmanagementservice/library 

**Build the library module**

Windows

    mvn.cmd clean install

Linux / macOS

    ./mvnw clean install

**Navigate to the application module**

    cd ../application

**Build the application module**

Windows

    mvn.cmd clean package

Linux / macOS

    ./mvnw clean package

**Run the jar**

    java -jar target/application-0.0.1-SNAPSHOT.jar

Open a new terminal and navigate to the webservice module:


### Build and run the web service application
    
    cd webservice 

Windows

    mvn.cmd clean install

Linux / macOS

    ./mvnw clean install

**Run the jar**

    java -jar target/webservice-0.0.1.jar

Open your browser to access the web service page at this address:
[http://localhost:8081/books](http://localhost:8081/books/create)