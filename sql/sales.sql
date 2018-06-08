create table sales(
	sale_id int auto_increment not null primary key,
	sale_date date not null,
	account_id int not null,
	category_id int not null,
	trade_name varchar(100) not null,
	unit_price int not null,
	sale_number int not null,
	note text default null
);

insert into sales(sale_id, sale_date, account_id, category_id, trade_name, unit_price, sale_number, note)values
(1, '2018/05/11',  1, 1, 'からあげ弁当', 450, 3, null),
(2, '2018/05/16',  2, 2, 'コカ・コーラ 500ml', 130, 5, null),
(3, '2018/05/25',  3, 1, 'あんぱん', 120, 10, null),
(4, '2018/05/29',  1, 1, 'あんぱん', 120, 10, null),
(5, '2018/05/31',  3, 2, 'オレンジジュース', 120, 5, '売れた');


