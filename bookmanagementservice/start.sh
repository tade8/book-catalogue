start() {
 cd library &&
 echo "Building and installing library service" &&
 if [[ "$OSTYPE" == darwin* || "$OSTYPE" == linux* ]]; then 
 	./mvnw clean install 
 else
	 mvn.cmd clean install
 fi

 echo "Building application service" &&
 cd ../application && ./mvnw clean package &&

 echo "Running the bookmanagementservice application"
 exec java -jar target/*.jar
} 
	
start
