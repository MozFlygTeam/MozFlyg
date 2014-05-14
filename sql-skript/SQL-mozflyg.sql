DROP TABLE account, airport, flight;

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

INSERT INTO flight (departing_from, arriving_to, time_departing, price) VALUES ("1","5", "2014-05-14 10:00:00", "3300");
INSERT INTO flight (departing_from, arriving_to, time_departing, price) VALUES ("2","3", "2014-05-14 12:30:00", "1053");
INSERT INTO flight (departing_from, arriving_to, time_departing, price) VALUES ("4","1", "2014-05-14 13:15:00", "5403");
INSERT INTO flight (departing_from, arriving_to, time_departing, price) VALUES ("1","4", "2014-06-14 09:45:00", "1870");
INSERT INTO flight (departing_from, arriving_to, time_departing, price) VALUES ("4","2", "2014-06-14 15:30:00", "2040");