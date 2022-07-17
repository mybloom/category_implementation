--depth1
insert into category (title, category_order) values ('아우터_테스트', 1);
insert into category (title, category_order) values ('상의_테스트', 2);

--depth2
insert into category (title, parent_category_id, category_order) values ('쟈켓_테스트',1, 2);
insert into category (title, parent_category_id, category_order) values ('가디건_테스트',1, 3);
insert into category (title, parent_category_id, category_order) values ('블루종_테스트',1, 1);

insert into category (title, parent_category_id, category_order) values ('반팔_테스트',2, 1);