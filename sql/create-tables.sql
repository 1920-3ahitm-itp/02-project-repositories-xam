CREATE TABLE Teacher(
    t_name varchar(50) NOT NULL,
    t_teacher_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    t_username varchar(50) NOT NULL,
    t_password varchar(32) NOT NULL,
    CONSTRAINT PK_Teacher PRIMARY KEY (t_teacher_id)
);

CREATE TABLE Student(
    s_class varchar(6) NOT NULL,
    s_name varchar(50) NOT NULL,
    s_student_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    s_username varchar(50) NOT NULL,
    s_password varchar(32) NOT NULL,
    CONSTRAINT PK_Student PRIMARY KEY (s_student_id)
);

CREATE TABLE Quiz(
    qui_quiz_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    qui_name varchar(50) NOT NULL,
    qui_description varchar(1000),
    qui_teacher_id int not null,
    CONSTRAINT PK_Quiz PRIMARY KEY (qui_quiz_id),
    CONSTRAINT FK_Quiz FOREIGN KEY (qui_teacher_id) REFERENCES Teacher(t_teacher_id)
);

CREATE TABLE Question(
    que_question_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    que_headline varchar(100),
    que_description varchar(1000),
    que_result varchar(100) NOT NULL,
    que_quiz_id int not null ,
    CONSTRAINT PK_Question PRIMARY KEY (que_question_id),
    CONSTRAINT FK_Question FOREIGN KEY (que_quiz_id) references Quiz(qui_quiz_id)
);

-- how to association table (Q_S)