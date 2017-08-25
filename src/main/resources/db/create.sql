SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS items (
  id int PRIMARY KEY auto_increment,
  userid int,
  name VARCHAR,
  location VARCHAR,
  pub BOOLEAN,
  description VARCHAR,
  barter BOOLEAN,
  type VARCHAR,
  variety VARCHAR,
  quantity VARCHAR,
  youpick BOOLEAN
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

