start() { 
echo "Building the webservice..."
if [[ "$OSTYPE" == linux* || "$OSTYPE" == darwin* ]]; then
    ./mvnw clean install
else
    mvnw.cmd clean install
fi

echo "Running the webservice..."
exec java -jar target/*.jar
}

start
