
USE adlister_db;

DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(240) NOT NULL,
    email VARCHAR(240) NOT NULL,
    password VARCHAR(255) NOT NULL,
    UNIQUE (username),
    PRIMARY KEY (id)
);

SELECT * FROM users;

CREATE TABLE ads (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id INT UNSIGNED NOT NULL,
    title VARCHAR(240) NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
);

SELECT * FROM ads;

CREATE TABLE IF NOT EXISTS categories(
  id INT UNSIGNED AUTO_INCREMENT,
  category VARCHAR(200),
  PRIMARY KEY (id)
);

SELECT * FROM categories;

CREATE TABLE IF NOT EXISTS ads_categories(
  ad_id INT UNSIGNED,
  category_id INT UNSIGNED,
  FOREIGN KEY(ad_id) REFERENCES ads(id),
  FOREIGN KEY(category_id) REFERENCES categories(id)
);

