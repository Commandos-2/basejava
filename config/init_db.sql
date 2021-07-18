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



