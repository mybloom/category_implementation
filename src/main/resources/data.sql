--category depth1
insert into category (title, category_order) values ('아우터', 1);
insert into category (title, category_order) values ('상의', 3);
insert into category (title, category_order) values ('바지', 2);

--category depth2
insert into category (title, parent_category_id, category_order) values ('쟈켓', 1, 3);
insert into category (title, parent_category_id, category_order) values ('가디건', 1, 1);
insert into category (title, parent_category_id, category_order) values ('블루종', 1, 2);

insert into category (title, parent_category_id, category_order) values ('긴팔', 2, 2);
insert into category (title, parent_category_id, category_order) values ('반팔', 2, 1);

insert into category (title, parent_category_id, category_order) values ('긴바지', 3, 3);
insert into category (title, parent_category_id, category_order) values ('미디바지', 3, 2);
insert into category (title, parent_category_id, category_order) values ('반바지', 3, 1);
