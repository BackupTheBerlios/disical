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
	startTime   timestamp not null,
	stopTime    timestamp not null check (startTime < stopTime),
	subject     varchar ( 32 ) not null,
	location    varchar ( 16 ) not null,
	descr		varchar ( 128 ) not null,
	
	primary key ( id )
);
create index appointments_login_idx 
	on appointments ( login );
create index appointments_subject_idx 
	on appointments ( login, subject );
create index appointments_time_idx
	on appointments ( login, startTime );

create table invitations (
	id          serial,
	login       varchar ( 16 ) not null references auth ( login ),
	startTime	timestamp not null,
	stopTime	timestamp not null check (startTime < stopTime),
	subject		varchar ( 32 ) not null,
	location    varchar ( 16 ) not null,
	descr		varchar ( 128 ) not null,
	
	primary key ( id )
);
create index invitations_login_idx
	on invitations ( login );



create table invited (	
	id          serial,
    invitation  integer not null references invitations ( id ),
    login       varchar ( 16 ) not null references auth ( login ),
	date        integer default null null references appointments ( id ),
	state       int2 default 0 check (state >= 0 and state <= 3),
	notify      boolean default false,

	primary key ( id )
);
create index invited_login_idx
	on invited ( login );

create function trigger_create_invited()
returns opaque
as 'begin if new.state != 0 then raise exception ''State of invited must be 0 on create.'';	end if;	if new.date != null raise exception ''Date not allowed for unaccepted invitation.''; end if; return new;	end;'
language 'plpgsql'; 

create trigger trigger_invited_create
before insert on invited
for each row execute procedure trigger_create_invited();

create function trigger_update_invited()
returns opaque
as 'begin if new.state == 2 and new.date == null then raise exception ''Date required for accepted invitation.''; end if; if new.state != 2 and new.date != null then raise exception ''Date not allowed for unaccepted invitation.''; end if; return new; end;'
language 'plpgsql'; 

create trigger trigger_invited_update
before update ON invited
for each row execute procedure trigger_update_invited();



