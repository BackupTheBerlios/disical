create table auth (
	login     varchar ( 16 ),
	password  varchar ( 16 ) not null,
	name      varchar ( 32 ) not null,
	email     varchar ( 32 ) not null,
	isAdmin   boolean default false,
	
	primary key ( login )
);
create index auth_email_idx on auth ( email );

create table appointments (
	id          serial,
	login       varchar ( 16 ) not null references auth ( login ),
	startDate   date not null,
	startTime   time not null,
	endDate     date not null,
	endTime     time not null,
	subject     varchar ( 32 ),
	location    varchar ( 16 ),
	
	primary key ( id )
);
create index appointments_login_idx 
	on appointments ( login );
create index appointments_start_idx 
	on appointments ( startDate, startTime );
create index appointments_subject_idx 
	on appointments ( subject );
	
