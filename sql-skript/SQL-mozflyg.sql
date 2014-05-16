DROP DATABASE mozflyg;
CREATE DATABASE mozflyg;
USE mozflyg;

CREATE TABLE airport(
	id SMALLINT(5) AUTO_INCREMENT NOT NULL,
	name CHAR(128) NOT NULL,
	city CHAR(128) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE account(
	id SMALLINT(5) AUTO_INCREMENT NOT NULL,
	username CHAR(128) NOT NULL,
	password CHAR(128) NOT NULL,
	isAdmin BOOLEAN DEFAULT 0,
	PRIMARY KEY (id)
);

CREATE TABLE flight(
	id SMALLINT(5) AUTO_INCREMENT NOT NULL,
	departing_from SMALLINT(5) NOT NULL,
	arriving_to SMALLINT(5) NOT NULL,
	time_departing DATETIME NOT NULL,
	price DOUBLE(10,2) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (departing_from) REFERENCES airport(id),
	FOREIGN KEY (arriving_to) REFERENCES airport(id)
);

CREATE TABLE booked_flight(
	account_id SMALLINT(5) NOT NULL,
	flight_id SMALLINT(5) NOT NULL,
	FOREIGN KEY(account_id) REFERENCES account(id),
	FOREIGN KEY(flight_id) REFERENCES flight(id)	
);

CREATE TABLE airplane_type
(
	id SMALLINT(5) NOT NULL,
	name CHAR(128) NOT NULL,
	passengerCapacity SMALLINT(5) NOT NULL,
	velocity SMALLINT(5) NOT NULL,
	fuelConsumption FLOAT(5,3) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO airplane_type VALUES (1, "Boeing 700", 149, 900, 4.084);
INSERT INTO airplane_type VALUES (2, "Boeing 800", 189, 880, 4.513); 
INSERT INTO airplane_type VALUES (3, "Airbus A380", 853, 900, 20.38); 

INSERT INTO airport (name, city) VALUES ("Narita International","Tokyo"); 
INSERT INTO airport (name, city) VALUES ("Arlanda","Stockholm");
INSERT INTO airport (name, city) VALUES ("Landvetter","Göteborg");
INSERT INTO airport (name, city) VALUES ("Småland Airport","Växjö");
INSERT INTO airport (name, city) VALUES ("Kalmar Airport","Kalmar");
INSERT INTO airport (name, city) VALUES ("Bergamo Airport","Milano");
INSERT INTO airport (name, city) VALUES ("Sturup","Malmö");
INSERT INTO airport (name, city) VALUES ("Anfa","Cassablanca");

INSERT INTO account (username, password, isAdmin) VALUES ("Kristoffer","123", "1");
INSERT INTO account (username, password, isAdmin) VALUES ("Magnus","123", "1");
INSERT INTO account (username, password, isAdmin) VALUES ("Kim","123", "1");
INSERT INTO account (username, password, isAdmin) VALUES ("Malin","123", "1");
INSERT INTO account (username, password, isAdmin) VALUES ("Alexander","123", "1");

INSERT INTO flight (departing_from, arriving_to, time_departing, price) VALUES ("1","5", "2014-05-14 10:00:00", "3300");
INSERT INTO flight (departing_from, arriving_to, time_departing, price) VALUES ("2","3", "2014-05-14 12:30:00", "1053");
INSERT INTO flight (departing_from, arriving_to, time_departing, price) VALUES ("4","1", "2014-05-14 13:15:00", "5403");
INSERT INTO flight (departing_from, arriving_to, time_departing, price) VALUES ("1","4", "2014-06-14 09:45:00", "1870");
INSERT INTO flight (departing_from, arriving_to, time_departing, price) VALUES ("4","2", "2014-06-14 15:30:00", "2040");