create sequence hibernate_sequence;

CREATE TABLE PERSON (
    id   INTEGER      NOT NULL,
    name VARCHAR(128) NOT NULL,
    status varchar(100) NOT NULL,
    PRIMARY KEY (id)
);