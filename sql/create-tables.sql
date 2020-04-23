CREATE TABLE Teacher(
    name varchar(50) NOT NULL,
    teacher_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username varchar(50) NOT NULL,
    password varchar(32) NOT NULL,
    CONSTRAINT PK_Teacher PRIMARY KEY (teacher_id)
);

CREATE TABLE Student(
    class varchar(6) NOT NULL,
    name varchar(50) NOT NULL,
    student_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username varchar(50) NOT NULL,
    password varchar(32) NOT NULL,
    CONSTRAINT PK_Student PRIMARY KEY (student_id)
);

CREATE TABLE Quiz(
    quiz_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name varchar(50) NOT NULL,
    description varchar(1000),
    teacher_id int not null,
    CONSTRAINT PK_Quiz PRIMARY KEY (quiz_id),
    CONSTRAINT FK_Quiz FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id)
);

CREATE TABLE Question(
    question_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    headline varchar(100),
    description varchar(1000),
    result varchar(100) NOT NULL,
    quiz_id int not null ,
    CONSTRAINT PK_Question PRIMARY KEY (question_id),
    CONSTRAINT FK_Question FOREIGN KEY (quiz_id) references Quiz(quiz_id)
);

-- how to association table (Q_S)