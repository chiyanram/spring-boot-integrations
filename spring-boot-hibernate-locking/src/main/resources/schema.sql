create sequence hibernate_sequence;

CREATE TABLE STUDENT (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    status varchar(100) NOT NULL,
    PRIMARY KEY (id)
);