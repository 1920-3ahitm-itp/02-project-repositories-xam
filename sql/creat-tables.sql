CREATE TABLE Teacher(
    name varchar(50) NOT NULL,
    id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username varchar(50) NOT NULL,
    password varchar(32) NOT NULL,
    CONSTRAINT PK_Teacher PRIMARY KEY (id)
);

CREATE TABLE Student(
    class varchar(6) NOT NULL,
    name varchar(50) NOT NULL,
    id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username varchar(50) NOT NULL,
    password varchar(32) NOT NULL,
    CONSTRAINT PK_Student PRIMARY KEY (id)
);

CREATE TABLE Quiz(
    id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name varchar(50) NOT NULL,
    description varchar(1000),
    CONSTRAINT PK_Quiz PRIMARY KEY (id)
);

CREATE TABLE Question(
    id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    headline varchar(100),
    description varchar(1000),
    result varchar(100) NOT NULL,
    CONSTRAINT PK_Question PRIMARY KEY (id)
);