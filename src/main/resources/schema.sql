 create table if not exists  users(
      username varchar(50) not null primary key,
      password varchar(50) not null,
      enabled boolean not null);

 create table if not exists  authorities (
      username varchar(50) not null,
      authority varchar(50) not null,
      constraint fk_authorities_users foreign key(username) references users(username));
        
create table if not exists userconnection (
  userId varchar(255) not null,
  providerId varchar(255) not null,
  providerUserId varchar(255),
  rank int not null,
  displayName varchar(255),
  profileUrl varchar(512),
  imageUrl varchar(512),
  accessToken varchar(1024) not null,
  secret varchar(255),
  refreshToken varchar(255),
  expireTime bigint,
  primary key (userId));


create table if not exists  UserProfile (
  userId varchar(255) not null,
  email varchar(255),
  firstName varchar(255),
  lastName varchar(255),
  name  varchar(255),
  username varchar(255),
  primary key (userId));
  
create table if not exists posts(
	id bigint not null, 
	title varchar(255), 
	body text, 
	parentId bigint, 
	threadId bigint,
	createdAt timestamp, 
	authorId varchar(255),
	tag1 varchar(255),
	tag2 varchar(255),
	tag3 varchar(255),
	tag4 varchar(255),
	tag5 varchar(255),
	imageUrl varchar(255),
	primary key (id)
);


/*
create sequence post_sequence;
create unique index ix_auth_username on authorities (username,authority);  
create unique index UserConnectionRank on UserConnection(userId, providerId, rank);
create unique index UserProfilePK on UserProfile(userId);
*/