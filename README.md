0. components of the project
0.1 database: hibernate + h2
0.2 REST: jersey
0.3 deployment: docker, tomcat

1. unpack the archive

2. go to the root folder of unpacked archive

3. build the project and package the war: run `mvn clean package -pl svc --am`

4. test

4.1 If you have docker installed, the simplest way to test it out is: go to the deploy folder, run `sh start-svrs.sh`. This will start a tomcat docker instance, and deploy the svc.war.
4.2 If you don't have docker, then try to deploy the war file together with the 'hibernate.cfg.xml', which should be copied to in the tomcat deployment folder for the application, something like '.../tomcat/webapps/svc/', then start the web server.
4.3 There is a test java program named 'SvcTest.java' located in folder 'svc/src/test/java/org/inov/assignment'. After the docker instance is up and running. Start you favorite java IDE and run the program. You'll see the output, which looks like:
===============================================
Customer [customer1] added
Customer [customer2] added
Customer [customer31] added
Customer [customer33] added
Customer [customer4] added
Failed to add customer: [customer4]
======= All customers ==========
[John Smith(uid=customer1,M)]
[Erik Lövquist(uid=customer2,M)]
[やまた(uid=customer31,M)]
[やまた(uid=customer33,F)]
[张晓(uid=customer4,F)]
======= by name 'John Smith' ==========
[John Smith(uid=customer1,M)]
======= by name 'やまた' ==========
[やまた(uid=customer31,M)]
[やまた(uid=customer33,F)]

*** Important Note: the docker deployment sets up https listening on port 8443, therefore the 'SvcTest.java' uses 'https://localhost:8443/webapi' as the default service endpoint url, if you deploy it on your own web server, the endpoint should be updated accordingly.
