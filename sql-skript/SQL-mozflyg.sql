DROP DATABASE mozflyg;
CREATE DATABASE mozflyg;
USE mozflyg;

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

CREATE TABLE airplane_type
(
	id SMALLINT(5) AUTO_INCREMENT NOT NULL,
	model_name CHAR(128) NOT NULL,
	passenger_capacity SMALLINT(5) NOT NULL,
	velocity SMALLINT(5) NOT NULL,
	fuel_consumption DOUBLE(10,2) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE flight
(
	id SMALLINT(5) AUTO_INCREMENT NOT NULL,
	departing_from SMALLINT(5) NOT NULL,
	arriving_to SMALLINT(5) NOT NULL,
	time_departing DATETIME NOT NULL,
	airplane_type SMALLINT(5) NOT NULL,
	price DOUBLE(10,2) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (departing_from) REFERENCES airport(id),
	FOREIGN KEY (arriving_to) REFERENCES airport(id),
	FOREIGN KEY (airplane_type) REFERENCES airplane_type(id)
);

CREATE TABLE booked_flight
(
	account_id SMALLINT(5) NOT NULL,
	flight_id SMALLINT(5) NOT NULL,
	FOREIGN KEY(account_id) REFERENCES account(id),
	FOREIGN KEY(flight_id) REFERENCES flight(id)	
);

INSERT INTO airplane_type VALUES (1,'Boeing 700',149,900,4.084);
INSERT INTO airplane_type VALUES (2,'Boeing 800',189,880,4.513);
INSERT INTO airplane_type VALUES (3,'Airbus A380',853,900,20.38);

INSERT INTO airport (name, city) VALUES ("Bromma","Stockholm"); 
INSERT INTO airport (name, city) VALUES ("Arlanda","Stockholm");
INSERT INTO airport (name, city) VALUES ("Landvetter","Göteborg");
INSERT INTO airport (name, city) VALUES ("Småland Airport","Växjö");
INSERT INTO airport (name, city) VALUES ("Jönköping Airport","Jönköping");

INSERT INTO account (username, password, isAdmin) VALUES ("Kristoffer","123", "1");
INSERT INTO account (username, password, isAdmin) VALUES ("Magnus","123", "1");
INSERT INTO account (username, password, isAdmin) VALUES ("Kim","123", "1");
INSERT INTO account (username, password, isAdmin) VALUES ("Malin","123", "1");
INSERT INTO account (username, password, isAdmin) VALUES ("Alexander","123", "1");

INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (1,5, '2014-05-14 10:00:00', 1, 3300);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (2,3, '2014-05-14 12:30:00', 2, 1053);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (4,1, '2014-05-14 13:15:00', 3, 5403);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (1,4, '2014-06-14 09:45:00', 1, 1870);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (4,2, '2014-06-14 15:30:00', 2, 4000);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (5,2, '2014-01-01 01:01:00', 3, 2040);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (1,4, '2014-07-01 01:30:00', 1, 2100);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (3,2, '2014-05-12 03:50:00', 2, 3240);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (5,2, '2014-11-13 014:35:00', 2, 3800);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (1,2, '2014-05-04 08:10:00', 3, 3230);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (4,3, '2014-10-03 11:20:00', 1, 2000);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (5,1, '2014-07-14 15:40:00', 3, 1200);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (2,3, '2014-08-13 16:20:00', 2, 3400);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (2,4, '2014-06-05 12:00:00', 1, 4000);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (2,4, '2014-06-06 12:00:00', 1, 4000);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (2,4, '2014-06-07 12:00:00', 2, 4000);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (2,3, '2014-06-05 14:40:00', 3, 3000);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (2,3, '2014-06-05 01:40:00', 1, 3000);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (2,4, '2014-06-05 01:30:00', 2, 3000);
INSERT INTO flight (departing_from, arriving_to, time_departing, airplane_type, price) VALUES (2,3, '2014-06-05 13:40:00', 1, 3000);

INSERT INTO booked_flight (account_id, flight_id) VALUES (2, 1);
INSERT INTO booked_flight (account_id, flight_id) VALUES (4, 2);
INSERT INTO booked_flight (account_id, flight_id) VALUES (2, 3);
INSERT INTO booked_flight (account_id, flight_id) VALUES (5, 4);
INSERT INTO booked_flight (account_id, flight_id) VALUES (1, 5);
INSERT INTO booked_flight (account_id, flight_id) VALUES (3, 1);

SELECT * FROM flight;