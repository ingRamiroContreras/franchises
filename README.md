# Getting Started

```sql

conectarse a la  BD desde EC2 sudo docker run -it --rm mysql:8.0 mysql -h nequi-mysqldatabase-r6qxakeauysp.c7g04ay8o057.us-east-2.rds.amazonaws.com -P 3306 -u admin -p

SHOW DATABASES;

USE nequi;

SHOW TABLES;

CREATE TABLE franchises (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE branches (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    franchise_id VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_franchise FOREIGN KEY (franchise_id) REFERENCES franchises(id)
);

CREATE TABLE products (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    stock INT,
    branch_id VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_branch FOREIGN KEY (branch_id) REFERENCES branches(id)
);
```