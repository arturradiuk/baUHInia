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
------------------------------------------------------------------

-- PlaceableObject class mapping:
create table placeable_objects
(
    id   varchar primary key not null,
    name varchar             not null
);

create table parameters_set
(
    po_id  varchar not null,
    _key   varchar not null,
    _value varchar not null,
    foreign key (po_id) references placeable_objects (id),
    unique (po_id, _key, _value) --?????????????????
);
------------------------------------------------------------------

-- MapObject class mapping:
create table map_objects
(
    id   varchar primary key not null,
    name varchar             not null
);

create table permissions_set
(
    mo_id  varchar not null,
    _key   varchar not null,
    _value varchar not null,
    foreign key (mo_id) references map_objects (id),
    unique (mo_id, _key, mo_id)
);

create table objects_set
(
    mo_id  varchar not null,
    _key   point not null,
    _value varchar   not null,
    foreign key (mo_id) references map_objects (id),
    foreign key (_value) references placeable_objects (id),
    unique (mo_id, _key, _value)
)
------------------------------------------------------------------

-- user maps

create table user_map_objects_set
(
    user_id int     not null,
    mo_id   varchar not null,
    _key    point   not null,
    _value  varchar not null,
    foreign key (mo_id) references map_objects (id),
    foreign key (_value) references placeable_objects (id),
    foreign key (user_id) references users (id),
    unique (mo_id, _value)
);

create table user_map_permissions_set -- todo should we drop this table, because permissions are common for all users?
(
    user_id int     not null,
    mo_id   varchar not null,
    _key    varchar not null,
    _value  varchar not null,
    foreign key (mo_id) references map_objects (id),
    foreign key (user_id) references users (id),
    unique (mo_id, _key, _value)
);