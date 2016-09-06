CREATE SCHEMA `mytest`;

use mytest;
drop table if exists UserInfo;
CREATE TABLE UserInfo (
    id INT AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    age INT not null,
    PRIMARY KEY (id)
);

INSERT INTO UserInfo(name,age) VALUES('Andy','12');
INSERT INTO UserInfo(name,age) VALUES('Tomy','24');
INSERT INTO UserInfo(name,age) VALUES('Lucy','18');
