create table auth (
	login		varchar ( 16 ),
	password 	varchar ( 16 ),
	name		varchar ( 32 ),
	email		varchar ( 32 ),
	isAdmin		boolean,
	
	primary key ( login ),
	unique ( email )
);
create unique index auth_email_idx on auth (email);

create table appointments (
	id			serial,
	login		varchar ( 16 ),
	startDate	date,
	startTime	time,
	endDate		date,
	endTime		time,
	subject		varchar ( 32 ),
	location	varchar ( 16 ),
	
	primary key ( id )
);
create index appointments_login_idx 
	on appointments (login);
create index appointments_start_idx 
	on appointments (startDate, startTime);
create index appointments_subject_idx 
	on appointments (subject);