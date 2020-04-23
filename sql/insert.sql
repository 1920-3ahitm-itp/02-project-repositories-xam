INSERT INTO Teacher(name, username, password)
VALUES ('Max Mustermann', 'm.mustermann', '1234');
INSERT INTO Teacher(name, username, password)
VALUES ('Lehrer', 'l.ehrer', 'test');

INSERT INTO Student(class, name, username, password)
VALUES ('3AHITM','Franz', 'it170123', '4321');
INSERT INTO Student(class, name, username, password)
VALUES ('2BHITM','Günther', 'it180123', 'password');

INSERT INTO Quiz(name, description)
VALUES ('MEDT-Test', 'Test über den Stoffbereich');
INSERT INTO Quiz(name, description)
VALUES ('SEW-Test', 'Test über Lanterna');

INSERT INTO Question(headline, description, result)
VALUES ('Was ist der Rückgabewert?', 'public int add() {return 1+1;}', '2');
INSERT INTO Question(headline, description, result)
VALUES ('Was ist der Rückgabetyp?', 'public int add() {return 1+1;}', 'int');