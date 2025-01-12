**Book catalogue application**

Open a terminal and navigate to the management service module

    cd managementservice 

Run the following command:

    mvn clean package
    java -jar target/managementservice.jar

Open a new terminal and navigate to the webservice module:
    
    cd webservice 
  
Run the following command to start the webservice:

    mvn clean package
    java -jar target/webservice.jar

Open your browser to access the web service page at this address:
[http://localhost:8081](http://localhost:8081)