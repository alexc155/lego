cd $1

mvn clean -f "./pom.xml"
mvn package -f "./pom.xml" -DmainClass=$2
mvn org.apache.maven.plugins:maven-antrun-plugin:run -f "./pom.xml"
mvn clean -f "./pom.xml"

cd ..