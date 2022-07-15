--category depth1
insert into category (title) values ('아우터');
insert into category (title) values ('상의');
insert into category (title) values ('바지');

--category depth2
insert into category (title, parent_category_id) values ('쟈켓',1);
insert into category (title, parent_category_id) values ('가디건',1);
insert into category (title, parent_category_id) values ('블루종',1);

insert into category (title, parent_category_id) values ('긴팔',2);
insert into category (title, parent_category_id) values ('반팔',2);

insert into category (title, parent_category_id) values ('긴바지',3);
insert into category (title, parent_category_id) values ('반바지',3);
