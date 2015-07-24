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

*please carefully install maven first
