-- User class mapping:
create table users
(
    id        bigserial PRIMARY KEY NOT NULL,
    firstName varchar,
    lastName  varchar,
    email     varchar unique,
    password  varchar,
    userType  varchar
);

create table objects_templates
(
    id           varchar primary key not null,
    length       int                 not null,
    height       int                 not null,
    terrain_type varchar             not null,
    object_type  varchar             not null,
    price        varchar             not null,
    heat_factor  float               not null
);


create table maps_metadata
(
    id            varchar primary key not null,
    last_modified date                not null,
    created       date                not null,
    user_id       int, -- if null then template
    foreign key (user_id) references users (id)
);