create table categories(
	category_id int auto_increment not null primary key,
	category_name varchar(50) not null,
	active_flg int not null default 1
);

insert into categories(category_id, category_name, active_flg)values
(1, '食料品', 1),
(2, 'お酒', 0),
(3, '飲料', 1);



