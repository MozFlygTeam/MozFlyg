DROP TABLE account, airport;

CREATE TABLE airport
(
id SMALLINT(5) AUTO_INCREMENT NOT NULL,
name CHAR(128) NOT NULL,
city CHAR(128) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE account
(
id SMALLINT(5) AUTO_INCREMENT NOT NULL,
username CHAR(128) NOT NULL,
password CHAR(128) NOT NULL,
isAdmin BOOLEAN DEFAULT 0,
PRIMARY KEY (id)
);

CREATE TABLE flight
(
id SMALLINT(5) AUTO_INCREMENT NOT NULL,
departing_from SMALLINT(5) NOT NULL,
arriving_to SMALLINT(5) NOT NULL,
time_departing DATETIME NOT NULL,
price DOUBLE(10,2) NOT NULL,
PRIMARY KEY (id)
);

INSERT INTO airport (name, city) VALUES ("Bromma","Stockholm"); 
INSERT INTO airport (name, city) VALUES ("Arlanda","Stockholm");
INSERT INTO airport (name, city) VALUES ("Landvetter","Göteborg");
INSERT INTO airport (name, city) VALUES ("Småland Airport","Växjö");
INSERT INTO airport (name, city) VALUES ("Jönköping Airport","Jönköping");

INSERT INTO account (username, password) VALUES ("Kristoffer","123");
INSERT INTO account (username, password) VALUES ("Magnus","123");
INSERT INTO account (username, password) VALUES ("Kim","123");
INSERT INTO account (username, password) VALUES ("Malin","123");
INSERT INTO account (username, password) VALUES ("Alexander","123");
