cd ..

mvn clean -f "/Users/aclark/Documents/GitHub.nosync/lego/lejos/ev3/pom.xml"
mvn package -f "/Users/aclark/Documents/GitHub.nosync/lego/lejos/ev3/pom.xml" -DmainClass=$1
mvn org.apache.maven.plugins:maven-antrun-plugin:run -f "/Users/aclark/Documents/GitHub.nosync/lego/lejos/ev3/pom.xml"
mvn clean -f "/Users/aclark/Documents/GitHub.nosync/lego/lejos/ev3/pom.xml"