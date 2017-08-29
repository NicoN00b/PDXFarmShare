SET MODE PostgreSQL;


CREATE TABLE IF NOT EXISTS items (
  id int PRIMARY KEY auto_increment,
  userid int,
  name VARCHAR,
  location VARCHAR,
  pub BOOLEAN,
  description VARCHAR,
  barter BOOLEAN
);


CREATE TABLE IF NOT EXISTS fruits(
  id int PRIMARY KEY auto_increment,
  userid int,
  name VARCHAR,
  location VARCHAR,
  pub BOOLEAN,
  description VARCHAR,
  barter BOOLEAN,
  variety VARCHAR,
  quantity VARCHAR,
  youpick BOOLEAN
);

CREATE TABLE IF NOT EXISTS veggies (
  id int PRIMARY KEY auto_increment,
  userid int,
  name VARCHAR,
  location VARCHAR,
  pub BOOLEAN,
  description VARCHAR,
  barter BOOLEAN,
  variety VARCHAR,
  quantity VARCHAR,
  youpick BOOLEAN
);

CREATE TABLE IF NOT EXISTS herbs (
  id int PRIMARY KEY auto_increment,
  userid int,
  name VARCHAR,
  location VARCHAR,
  pub BOOLEAN,
  description VARCHAR,
  barter BOOLEAN,
  variety VARCHAR,
  quantity VARCHAR,
  youpick BOOLEAN
);

CREATE TABLE IF NOT EXISTS others (
  id int PRIMARY KEY auto_increment,
  userid int,
  name VARCHAR,
  location VARCHAR,
  pub BOOLEAN,
  description VARCHAR,
  barter BOOLEAN,
  quantity VARCHAR
);

CREATE TABLE IF NOT EXISTS users (
  id int PRIMARY KEY auto_increment,
  username VARCHAR,
  useraddress VARCHAR,
  userzip INTEGER,
  userphone VARCHAR,
  useremail VARCHAR,
  userbio VARCHAR,
  userpass VARCHAR

);

