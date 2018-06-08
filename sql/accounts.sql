create table accounts(
	account_id int auto_increment not null primary key,
	name varchar(20) not null,
	mail varchar(100) not null,
	password varchar(32) not null,
	authority int not null default 1
);

insert into accounts (account_id, name, mail, password, authority)values
(1, 'イチロー', 'ichiro@sak.com', MD5('ichiro@sak.com'), 1),
(2, 'ダルビッシュ 有', 'yu.dalvish@sak.com', MD5('yu.dalvish@sak.com'), 1),
(3, '田中将大', 'masahiro.tanaka@sak.com', MD5('masahiro.tanaka@sak.com'), 0);