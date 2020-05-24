cd ..

mvn clean -f "/Users/aclark/Documents/GitHub.nosync/lego/ev3/pom.xml"
mvn package -f "/Users/aclark/Documents/GitHub.nosync/lego/ev3/pom.xml"
mvn org.apache.maven.plugins:maven-antrun-plugin:run -f "/Users/aclark/Documents/GitHub.nosync/lego/ev3/pom.xml"
mvn clean -f "/Users/aclark/Documents/GitHub.nosync/lego/ev3/pom.xml"