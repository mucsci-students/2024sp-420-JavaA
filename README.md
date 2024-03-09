# 2024sp-420-Java2
1. Java Runtime Version of 11 or higher https://www.oracle.com/java/technologies/downloads/#jdk21-windows

2. Git Installed https://github.com/git-guides/install-git

3. Download maven https://maven.apache.org/download.cgi

4. Install maven https://maven.apache.org/install.html

5. git clone https://github.com/mucsci-students/2024sp-420-Java2.git

6. from the java2 directory run "mvn clean install"

7. from the java2 directory run "mvn clean package spring-boot:repackage"

8. from the java2 directory run "java -jar .\target\java2-1.0-SNAPSHOT.jar"

9. When loading json files it is expected that they are in the java2 directory

10. Run tests by using "mvn clean test" from the java2 directory

11. Run specific tests by using "mvn test -Dtest=TestClassName#MethodName" where TestClassName is the name
of the file you want to run and Methodname is the name of the test you want to run, you can also use *
as the MethodName to run all tests of that class.

12. To run the CLI use "java -jar .\target\java2-1.0-SNAPSHOT.jar cli" when starting the program