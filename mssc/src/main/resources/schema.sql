create table Post (
    id int auto_increment primary key,
    title varchar(255) not null,
    content text not null
);

insert into Post(title, content)  values ('Hello, World!','My first Blog Post');