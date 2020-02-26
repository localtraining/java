DROP TABLE IF EXISTS users;
 
CREATE TABLE users (
  id 	INT 				AUTO_INCREMENT 	PRIMARY KEY,
  name 		VARCHAR(60) 		NOT NULL,
  cpf		VARCHAR(15) 		NOT NULL,
  email 	VARCHAR(254)		NOT NULL,
  created	TIMESTAMP			DEFAULT CURRENT_TIMESTAMP	
);

insert into users values (null,'Darth Vader', '12345678910', 'vader@deathstar.dark', {ts '2000-01-01 08:30:00.00'});
insert into users values (null,'Luke Skywalker', '10987654321', 'luke@resistance.force', {ts '2000-01-02 08:30:00.00'});

----------------------------------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS work_time;
 
CREATE TABLE work_time (
  timeid 	INT 				AUTO_INCREMENT 	PRIMARY KEY,
  userid	INT			 		NOT NULL,
  created	TIMESTAMP			DEFAULT CURRENT_TIMESTAMP,
  type 		INT					NOT NULL
);

CREATE INDEX work_time_idx on work_time(created);

-- Trabalhou sem intervalo
insert into work_time values (null,2, {ts '2020-02-18T08:30:00.00'}, 0);
insert into work_time values (null,2, {ts '2020-02-18T17:32:40.00'}, 1);

-- Trabalhou com intervalo
insert into work_time values (null,2, {ts '2020-02-19T09:30:00.00'}, 0);
insert into work_time values (null,2, {ts '2020-02-19T12:00:00.00'}, 1);
insert into work_time values (null,2, {ts '2020-02-19T13:00:00.00'}, 0);
insert into work_time values (null,2, {ts '2020-02-19T17:30:00.00'}, 1);