CREATE TABLE OrderLine (
    id   INTEGER  IDENTITY(1,1)    NOT NULL,
    name NVARCHAR(128) NOT NULL,
    createdDate datetime not null,
    PRIMARY KEY (id)
);

ALTER TABLE OrderLine ADD modifiedDate datetime;
