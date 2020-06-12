INSERT INTO Teacher(t_name, t_username, t_password)
VALUES ('Max Mustermann', 'm.mustermann', '1234');
INSERT INTO Teacher(t_name, t_username, t_password)
VALUES ('Lehrer', 'l.ehrer', 'test');

INSERT INTO Student(s_class, s_name, s_username, s_password)
VALUES ('3AHITM','Franz', 'it170123', '4321');
INSERT INTO Student(s_class, s_name, s_username, s_password)
VALUES ('2BHITM','Günther', 'it180123', 'password');

INSERT INTO Quiz(qui_name, qui_description, qui_teacher_id)
VALUES ('MEDT-Test', 'Test über den Stoffbereich', 1);
INSERT INTO Quiz(qui_name, qui_description, qui_teacher_id)
VALUES ('SEW-Test', 'Test über Lanterna', 2);

INSERT INTO Question(que_headline, que_description, que_result, que_quiz_id)
VALUES ('Was ist der Rückgabewert?', 'public int add() {return 1+1;}', '2', 1);
INSERT INTO Question(que_headline, que_description, que_result, que_quiz_id)
VALUES ('Was ist der Rückgabetyp?', 'public int add() {return 1+1;}', 'int', 1);