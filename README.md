Initial API for doing basic conversation stuff

To Run the code : 

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
7. to run, mvn spring-boot:run
*please carefully install maven first


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


