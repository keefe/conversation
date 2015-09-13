Initial API for doing basic conversation stuff

To Run the code for the first time only:

1. Add this entry to /etc/hosts
127.0.0.1	categorize.us
2. go to postgresql.com and download the mac os x app (or whatever)
3. open the psql terminal
4. run these commands (substitute the password with the one in application.properties)
 create user categorizeus with password 'password-from-file';   
create database categorizeus;
grant all privileges on database "categorizeus" to categorizeus;
5. If you want to run multiple installs, just change the data directory on postgres and do this again. 
6. In schema.sql the first time you run, you should uncomment the create index so these get created ( block at the bottom of file)
7. to run, mvn spring-boot:run or scripts/run.sh

Now, we will populate with some data so that the project looks realish:

8. navigate to http://categorize.us:8080/ and login using Google+
9. Checkout the accession project
10. go to that directory and run mvn exec:java
11. Have some coffee and wait for the database to load

*please carefully install java 1.8 and maven first if you have not already done so


Client Side Templating is done with handlebars
http://handlebarsjs.com/
This is used for live templating, when the data is loaded from ajax and so forth. 

Thymeleaf is used for server side templating, this is used for when the server generates data. 
This is used for auth stuff and eventually will be used to generate pages that are good to do pagerank with. 

http://www.thymeleaf.org/documentation.html

When making UI updates, the files in src/main/resource/static are updated AT RUNTIME
This means that when making changes there only (js, css, client side templates) That it is not necessary to restart the server.
However, a hard reload of the website is necessary, which is command shift r on chrome in mac. 

To make an update with new database changes, you need to go into psql and run
drop table posts;
drop table userconnection;


tables are currently in flux, need to figure out cross reference table mapping
