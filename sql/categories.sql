create table categories(
	category_id int auto_increment not null primary key,
	category_name varchar(50) not null,
	active_flg int not null default 1
);

insert into categories(category_id, category_name, active_flg)values
(1, '水産・畜産・農産加工品', 1),
(2, '生鮮・チルド・冷凍食品', 1),
(3, '乳油製品・調味料・調味食品', 1),
(4, '麺類', 1),
(5, 'スープ類', 1),
(6, '菓子類', 1),
(7, '嗜好飲料', 1),
(8, '飲料', 1),
(9, '酒類', 1),
(10, '氷・アイスクリーム類', 1),
(11, 'デザート類', 1),
(12, '健康サポート', 1),
(13, '化粧品', 1),
(14, 'トイレタリー', 1),
(15, '文具・仏具・雑貨', 1),
(16, 'たばこ', 1),
(17, '水産', 0),
(18, '畜産', 0),
(19, '農産加工品', 0),
(20, '生鮮', 0),
(21, 'チルド', 0),
(22, '冷凍食品', 0);