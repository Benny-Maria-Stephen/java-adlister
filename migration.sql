
USE adlister_db;

DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS ADS;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS ads_categories;


CREATE TABLE users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(240) NOT NULL,
    email VARCHAR(240) NOT NULL,
    password VARCHAR(255) NOT NULL,
    UNIQUE (username),
    PRIMARY KEY (id)
);

CREATE TABLE ads (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id INT UNSIGNED NOT NULL,
    title VARCHAR(240) NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,
    UNIQUE (user_id, title)
);

CREATE TABLE IF NOT EXISTS categories(
  id INT UNSIGNED AUTO_INCREMENT,
  category VARCHAR(200),
  UNIQUE (category),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ads_categories(
  ad_id INT UNSIGNED,
  category_id INT UNSIGNED,
  FOREIGN KEY(ad_id) REFERENCES ads(id),
  FOREIGN KEY(category_id) REFERENCES categories(id)
);

use adlister_db;

select * from categories;
select * from ads;
select * from ads_categories;
select * from users;

delete
delete from ads where id = 13;