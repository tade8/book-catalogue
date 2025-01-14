**Book catalogue application**

Open a terminal and navigate to the management service module

    cd managementservice 

Run the following commands:

Windows

    ./mvnw clean package
    java -jar target/managementservice-0.0.1-SNAPSHOT.jar

Linux/macOS

    ./mvnw clean package
    java -jar target/managementservice-0.0.1-SNAPSHOT.jar

Open a new terminal and navigate to the webservice module:
    
    cd webservice 
  
Run the following commands to start the webservice:

Windows

    ./mvnw clean package
    java -jar target/webservice-0.0.1-SNAPSHOT.jar

Linux/macOS

    ./mvnw clean package
    java -jar target/webservice-0.0.1-SNAPSHOT.jar

Open your browser to access the web service page at this address:
[http://localhost:8081](http://localhost:8081)