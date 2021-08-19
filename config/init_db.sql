create table resume
(
    uuid      char(36) not null
        constraint resume_pk
            primary key,
    full_name text     not null
);

alter table resume
    owner to postgres;

create unique index resume_uuid_uindex
    on resume (uuid);

create table contact
(
    id          serial   not null
        constraint contact_pk
            primary key,
    type        text     not null,
    value       text     not null,
    resume_uuid char(36) not null
        constraint contact_resume_uuid_fk
            references resume
            on update restrict on delete cascade
);

alter table contact
    owner to postgres;

create unique index "contact_uuid-type_index"
    on contact (resume_uuid, type);

create table section
(
    resume_uuid char(36) not null
        constraint section_resume_uuid_fk
            references resume
            on delete cascade,
    type text,
    value text,
    id serial not null
        constraint section_pk
            primary key
);

alter table section owner to postgres;

create table if not exists organization_section
(
	id char(36) not null
		constraint organization_section_pk
			primary key,
	resume_uuid text not null
		constraint organization_section_resume_uuid_fk
			references resume
				on delete cascade,
	name text not null,
	url text,
	type text not null
);

alter table organization_section owner to postgres;

create unique index if not exists organization_section_id_uindex
	on organization_section (id);

create table if not exists position
(
	id_organization_section char(36)
		constraint position_organization_section_id_fk
			references organization_section
				on delete cascade,
	heading text,
	value text,
	initial_date date not null,
	end_date date
);

alter table position owner to postgres;



