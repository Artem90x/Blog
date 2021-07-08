drop table if exists captcha_codes;
drop table if exists global_settings;
drop table if exists post;
drop table if exists post_comments;
drop table if exists post_votes;
drop table if exists tag;
drop table if exists tags2post;
drop table if exists user;

create table captcha_codes
(
    id integer not null auto_increment,
    code tinytext not null,
    secret_code tinytext not null,
    time datetime not null,
    primary key (id)
) engine=MyISAM;

create table global_settings
(
    id integer not null auto_increment,
    code varchar(255) not null,
    name varchar(255) not null,
    value varchar(255) not null,
    primary key (id)
) engine=MyISAM;

create table posts
(
    id integer not null auto_increment,
    is_active tinyint(1) not null,
    moderation_status enum('NEW','ACCEPTED','DECLINED') default 'NEW' not null,
    text text not null,
    time timestamp not null,
    title varchar(255) not null,
    view_count integer not null,
    moderator_id integer,
    user_id integer not null,
    primary key (id)
) engine=MyISAM;

create table post_comments
(
    id integer not null auto_increment,
    parent_id integer,
    text text not null,
    time datetime not null,
    post_id integer not null,
    user_id integer not null,
    primary key (id)
) engine=MyISAM;

create table post_votes
(
    id integer not null auto_increment,
    time datetime not null,
    value tinyint(1) not null,
    post_id integer not null,
    user_id integer not null,
    primary key (id)
) engine=MyISAM;

create table tags
(
    id integer not null auto_increment,
    name varchar(255) not null,
    primary key (id)
) engine=MyISAM;

create table tag2post
(
    id integer not null auto_increment,
    post_id integer not null,
    tag_id integer not null,
    primary key (id)
) engine=MyISAM;

create table users
(
    id integer not null auto_increment,
    code varchar(255),
    email varchar(255) not null,
    is_moderator tinyint not null,
    name varchar(255) not null,
    password varchar(255) not null,
    photo text ,
    reg_time datetime not null,
    primary key (id)
) engine=MyISAM;