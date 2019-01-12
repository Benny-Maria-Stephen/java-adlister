
# select * from ads;
insert into users (username, email, password) values ('user1', 'user1@gmail.com', 'password'), ('user2', 'user2@gmail.com', 'word'), ('user3', 'user3@gmail.com', 'word');
insert into ads (user_id, title, description) values (1, 'hello', 'description description'), (2, 'goodbye', 'description description'), (3, 'goodbye5', 'description description5');
insert into ads (user_id, title, description) values (1, 'hello4', 'description4'), (1, 'hello5', 'description 5');

insert into categories (category) values ('category1') , ('category2'),('category3'), ('category4'), ('category5');
insert into ads_categories (ad_id, category_id) values (2,1), (2,2);

use adlister_db;