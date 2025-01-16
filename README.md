**Book catalogue application**

Open a terminal and navigate to the book management service module

    cd bookmanagementservice/application 

Run the following commands:

Windows

    mvn.cmd clean install
    java -jar target/application-0.0.1-SNAPSHOT.jar

Linux / macOS

    ./mvnw clean install
    java -jar target/application-0.0.1-SNAPSHOT.jar

Open a new terminal and navigate to the webservice module:
    
    cd webservice 
  
Run the following commands to start the webservice:

Windows

    mvn.cmd clean install
    java -jar target/webservice-0.0.1.jar

Linux / macOS

    ./mvnw clean install
    java -jar target/webservice-0.0.1.jar

Open your browser to access the web service page at this address:
[http://localhost:8081/books](http://localhost:8081/books/create)