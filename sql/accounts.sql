create table accounts(
	account_id int auto_increment not null primary key,
	name varchar(20) not null,
	mail varchar(100) not null,
	password varchar(32) not null,
	authority int not null default 1
);

insert into accounts (account_id, name, mail, password, authority)values
(1, 'Admin', 'yuichi.sudo@ssie.jp', MD5('0000'), 10),
(2, '土樋奎太', 'tsuchitoi.keita@mail.tokyosystem.co.jp', MD5('0000'), 1),
(3, '笹原颯', 'sasahara.hayate@mail.tokyosystem.co.jp', MD5('0000'), 1),
(4, '中村楓佳', 'nakamura.fuka@mail.tokyosystem.co.jp', MD5('0000'), 1),
(5, '阪本真由', 'sakamoto.mayu@mail.tokyosystem.co.jp', MD5('0000'), 1),
(6, '叶内森', 'kanauchi.shin@mail.tokyosystem.co.jp', MD5('0000'), 1),
(7, '野島紳司', 'nojima.shinji@mail.tokyosystem.co.jp', MD5('0000'), 1),
(8, '木村有理人', 'kimura.arito@mail.tokyosystem.co.jp', MD5('0000'), 1);
