DROP TABLE IF EXISTS designer_type;
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS pattern;
DROP TABLE IF EXISTS designer;


CREATE TABLE designer (
	designer_id int NOT NULL AUTO_INCREMENT,
	name varchar(60),
	website varchar(128),
	email varchar(128),
	PRIMARY KEY(designer_id)
);
CREATE TABLE pattern (
	pattern_id int NOT NULL AUTO_INCREMENT,
	designer_id int,
	name varchar(40),
	size varchar(20),
	difficulty varchar(20),
	notes varchar(128),
	PRIMARY KEY(pattern_id),
	FOREIGN KEY(designer_id) REFERENCES designer (designer_id) ON DELETE CASCADE
);

CREATE TABLE type (
	type_id int NOT NULL AUTO_INCREMENT,
	name varchar(60),
	PRIMARY KEY(type_id)
);
CREATE TABLE designer_type (
	designer_id int NOT NULL,
	type_id int NOT NULL,
	FOREIGN KEY(designer_id) REFERENCES designer (designer_id) ON DELETE CASCADE,
	FOREIGN KEY(type_id) REFERENCES type (type_id) ON DELETE CASCADE
)
