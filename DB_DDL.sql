drop schema Application_Monitor;

create schema if not exists Application_Monitor;

use Application_Monitor;

create table if not exists Roles (
	id int not null AUTO_INCREMENT,
	name text,
	primary key (id)
);

create table if not exists Status (
	id int not null AUTO_INCREMENT,
	name text,
	primary key (id)
);

create table if not exists Terms (
	id int not null AUTO_INCREMENT,
	name text,
	primary key (id)
);

create table if not exists Application (
	id int not null AUTO_INCREMENT,
	firm_name text,
	role_id int DEFAULT 2,
	status_change_date datetime DEFAULT CURRENT_TIMESTAMP,
	term_id int DEFAULT 1,
	location text,
	status_id int DEFAULT 1,
	primary key (id),
	foreign key (role_id) references Roles(id),
	foreign key (status_id) references Status(id),
	foreign key (term_id) references Terms(id)
);

insert into Roles values(1, "SOFTWARE_ENGINEER");
insert into Roles values(2, "SOFTWARE_ENGINEER_INTERN");

insert into Status values(1, "PENDING");
insert into Status values(2, "REJECTED");
insert into Status values(3, "ACCEPTED");
insert into Status values(4, "INTERVIEW_PENDING");
insert into Status values(5, "ACCEPTANCE_PENDING");

insert into Terms values(1, "FALL");
insert into Terms values(2, "SUMMER");
insert into Terms values(3, "SPRING");
insert into Terms values(4, "WINTER");
insert into Terms values(5, "FULL_TIME");


