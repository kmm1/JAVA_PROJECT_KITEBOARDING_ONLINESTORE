DROP DATABASE shop;

CREATE DATABASE shop;

USE shop;

CREATE TABLE user (
  id                INT AUTO_INCREMENT,
  name              VARCHAR(20),
  email             VARCHAR(60),
  #   bcrypt has 60 digits in password!!!
  password          VARCHAR(60),
  role              VARCHAR(10),
  enabled           VARCHAR(2),
  registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE address (
  id               INT AUTO_INCREMENT,
  recieverName     VARCHAR(255),
  recieverLastName VARCHAR(255),
  addressLineOne   VARCHAR(50),
  addressLineTwo   VARCHAR(255),
  city             VARCHAR(255),
  state            VARCHAR(255),
  country          VARCHAR(50),
  zip              VARCHAR(50),
  telephone        VARCHAR(255),
  additionalInfo   TEXT,
  user_id          INT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE category (
  id   INT AUTO_INCREMENT,
  name VARCHAR(50),
  PRIMARY KEY (id)
);

CREATE TABLE product (
  id               INT AUTO_INCREMENT,
  name             VARCHAR(255),
  description      TEXT,
  price            VARCHAR(10),
  amount_available INT,
  availability     VARCHAR(10),
  image_url        TEXT,
  category_id      INT,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE orders (
  id         INT       AUTO_INCREMENT,
  user_id    INT,
  address_id INT,
  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  status     VARCHAR(20),
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE product_orders (
  product_id INT,
  order_id   INT,
  PRIMARY KEY (product_id, order_id),
  FOREIGN KEY (product_id) REFERENCES product (id),
  FOREIGN KEY (order_id) REFERENCES orders (id)
);

CREATE TABLE review (
  id         INT       AUTO_INCREMENT,
  user_id    INT  NOT NULL,
  product_id INT  NOT NULL,
  content    TEXT NOT NULL,
  date       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id),
  FOREIGN KEY (product_id) REFERENCES product (id)
);

INSERT INTO user (name, email, password, role, enabled) VALUES ('admin', 'km@gmail.com',
                                                                '$2a$10$mJ9BoO..coAAIAl.KKrHluCx/pgPN18VkPzJ7m/osxJebvbQEX20W',
                                                                'ADMIN', 1);
INSERT INTO user (name, email, password, role, enabled) VALUES ('user1', 'user1@gmail.com',
                                                                '$2a$10$yJbb9W1Q8ETN8xsHB.I5A.Ract35ygslJjdGyP.lYsckqVAPOfFKS',
                                                                'USER', 1);
INSERT INTO user (name, email, password, role, enabled) VALUES ('user2', 'user2@gmail.com',
                                                                '$2a$10$yJbb9W1Q8ETN8xsHB.I5A.Ract35ygslJjdGyP.lYsckqVAPOfFKS',
                                                                'USER', 1);
# password for admin in BCRYPT: admin
# password for users in BCRYPT: smth

INSERT INTO address (recieverName, recieverLastName, addressLineOne, addressLineTwo, city, state, country, zip, telephone, additionalInfo, user_id)
VALUES
  ('Ivan', 'Ivanov', 'Corner str. 1', 'addressLineTwo', 'Minsk', 'Minsk region', 'BELARUS', '220025',
           '+375447000000', 'additionalInfo', 2);

INSERT INTO address (recieverName, recieverLastName, addressLineOne, addressLineTwo, city, state, country, zip, telephone, additionalInfo, user_id)
VALUES
  ('Lena', 'Zaiceva', 'Ozernaya str. 29', 'addressLineTwo', 'Raubichi', 'Minsk region', 'BELARUS', '220025',
           '+375296000000', 'additionalInfo', 3);

INSERT INTO category (name) VALUES ('KITE');
INSERT INTO category (name) VALUES ('KITEBOARD');
INSERT INTO category (name) VALUES ('ACCESSORY');

INSERT INTO product (name, description, price, amount_available, availability, image_url, category_id)
VALUES ('50/FIFTY', '50/Fifty is the Superman of kites. It will do anything', 1119.00, 10, 1, '50fifty_01.JPG', 1);

INSERT INTO product (name, description, price, amount_available, availability, image_url, category_id)
VALUES ('mr-big', 'This kite has a huge surface with a flat', 999.00, 5, 0, 'mr-big_03.JPG', 1);

INSERT INTO product (name, description, price, amount_available, availability, image_url, category_id)
VALUES ('NO. 1', 'Nobile No.1 is a professional kite designed for', 599.00, 3, 1, 'no1_02.JPG', 1);

INSERT INTO product (name, description, price, amount_available, availability, image_url, category_id)
VALUES ('T5', 'The Nobile T5 Kite is simply one of the best kites', 899.00, 1, 1, 't5_04.JPG', 1);

INSERT INTO product (name, description, price, amount_available, availability, image_url, category_id)
VALUES ('Tride-NBL', 'The NBL is the classic, all-round Nobile board', 479.00, 2, 1, 'tride-nbl_01.JPG', 2);

INSERT INTO review (user_id, product_id, content) VALUES (2, 1, 'this is my favorite kite');
INSERT INTO review (user_id, product_id, content) VALUES (3, 1, 'my best kite');

INSERT INTO orders (user_id, address_id, order_date, status) VALUES (2, 1, '2000-01-01 00:00:00', 'ACCEPTED');
INSERT INTO orders (user_id, address_id, order_date, status) VALUES (2, 1, '2000-01-01 00:00:00', 'ACCEPTED');
INSERT INTO orders (user_id, address_id, order_date, status) VALUES (3, 2, '2000-01-01 00:00:00', 'PAID');
INSERT INTO orders (user_id, address_id, order_date, status) VALUES (2, 1, '2000-01-02 00:00:00', 'ACCEPTED');
INSERT INTO orders (user_id, address_id, order_date, status) VALUES (2, 1, '2000-01-01 00:00:00', 'ACCEPTED');

INSERT INTO product_orders (product_id, order_id) VALUES (1, 1);
INSERT INTO product_orders (product_id, order_id) VALUES (2, 2);
INSERT INTO product_orders (product_id, order_id) VALUES (1, 3);
INSERT INTO product_orders (product_id, order_id) VALUES (2, 4);
INSERT INTO product_orders (product_id, order_id) VALUES (2, 5);


