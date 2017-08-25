SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS items (
  id int PRIMARY KEY auto_increment,
  userid int,
  name VARCHAR,
  location VARCHAR,
  pub BOOLEAN,
  description VARCHAR,
  barter BOOLEAN,
);

CREATE TABLE IF NOT EXISTS user (
  id int PRIMARY KEY auto_increment,
  username VARCHAR,
  useraddress VARCHAR,
  userzip INTEGER,
  userphone INTEGER,
  useremail VARCHAR,
  userbio VARCHAR,
  userpass VARCHAR

);

