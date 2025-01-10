The book catalogue application

Open a terminal and navigate to the management service module

    cd managementservice 

Run the following command:

Windows Command Prompt:

    mvnw.cmd spring-boot:run 
Linux(Git bash) / MacOS(Z shell):

    ./mvnw spring-boot:run

Open a new terminal and navigate to the webservice module
    
    cd webservice 
  
Run the following command to start the webservice:
    
Windows(Command Prompt or Powershell):

    mvnw.cmd spring-boot:run
    
Linux(Git bash) / MacOS(Z shell):
    
    ./mvnw spring-boot:run

Open your browser to access the web service page at this address:
http://localhost:8081