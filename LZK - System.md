# XAM

Webanwendung, weil Franklyn runterladen is ned so suppa

### Startseite

auf der Startseite kann man auswählen ob man sich als Lehrer oder als Schüler anmelden will.

### Log-In

##### als Lehrer

Als Lehrer kann man sich entweder anmelden mit Name und Passwort, oder man erstellt sich einen Account bei dem man seine Fächer, Klassen, ... auch angeben kann.

##### als Schüler

Wenn man sich als Schüler anmelden will, meldet man sich mit seinen normalen Anmeldedaten an (Matrikelnummer und Passwort). Dadurch wird automatisch bekannt in welche Klasse dieser Schüler geht

##### als Programmierer

Lehrer und Schüler müssen natürlich in einer Datenbank persistiert werden. Diese werden voraussichtlich in 2 Tabellen gespeichert. Als PK dient bei dem Schüler die Matrikelnummer, bei den Lehrern wird wahrscheinlich der Name verwendet werden. 

### Menü

##### als Lehrer

Der angemeldete Lehrer sieht auf der ersten Seite alle seine aktuellen Quizzes (die noch nicht durchgeführt wurden aber schon erstellt sind) und eine Möglichkeit, ein neues Quiz zu erstellen. In einem Unterordner kann er seine vergangenen Quizzes ansehen um evtl. Fragen wiederzuverwenden o.Ä.

Wenn er auf ein aktuelles Quiz klickt, kann er dieses bearbeiten oder starten.

Wenn er ein neues erstellt, gibt er Fragen an, für welche Klasse und für welches Fach dieses Quiz ist und erstellt ein Passwort, mit welchem die Schüler zum Quiz antreten können. Evtl. kann er auch angeben wie dieses Quiz gewichtet ist.
Möglicherweise bekommt das Quiz beim erstellen automatisch eine Quiznummer welche die Schüler eingeben um zum Quiz zu gelangen

Als weiteres Feature ist geplant, dass der Lehrer von alle Ergebnisse ihrer Schüler in einem Diagramm o.Ä. betrachten kann und automatisch die Note der Quizzes ausgerechnet wird.

##### als Schüler

um als Schüler zu dem Quiz zu gelangen welches man machen muss, gibt man die Quiznummer an und das Passwort welches vom Lehrer angelegt wurde.

Außerdem soll man auch als Schüler jederzeit seine aktuelle Note in jedem Fach einsehen können, in welchem das System benutzt wird. Deswegen soll auch für den Schüler ein Diagramm von jedem Quiz in dem jeweiligen Fach sichtbar sein und die Gesamtnote ausgerechnet werden.

##### als Programmierer

Benötigte Daten: 

+ Quizzes
+ Ergebnisse für Lehrer
+ Ergebnisse für Schüler
+ Anmeldedaten der Schüler

Die detaillierte Umsetzung ist noch nicht planbar, da wir nicht wissen ob wir z.B. an die Anmeldedaten der Schüler kommen. Außerdem haben wir noch keine Erfahrung mit Datenbanken und können daher nicht absehen ob dies alles möglich ist.



### Quizzen

##### als Lehrer

als Lehrer sieht man im Laufe des Quizzes den Fortschritt aller Schüler, evtl auch richtig und falsch beantwortete Fragen.

Außerdem hat er die möglich das Quiz zu beenden oder einzelne Schüler auszuschließen weil sie z.B. geschummelt haben.

##### als Schüler

der Schüler sieht oben eine kleine Aufgabenstellung (z.B. erstelle einen leeren Konstruktor für die Klasse Person, erstelle Getter für folgende Variablen,...). In einem Code-Feld unterhalb kann er dann diese Aufgabe lösen. Der Lehrer kann auch beim Erstellen des Quizzes einen Basis Code angeben (Variablen deklarieren, ...) mit denen der Schüler dann arbeiten kann.

Nachdem man mit einer Frage fertig ist, drückt man auf weiter und kommt zur nächsten Frage. Wenn alle Fragen durch sind sieht man noch einmal eine Übersicht aller Fragen und gibt erst danach das Quiz zur Bewertung frei. 

Sobald man das Quiz abgegeben hat sieht man sofort das Ergebnis das man erreicht hat.

##### als Programmierer

wir wollen die Quizzes so implementieren, das der Lehrer Unit-Tests schreibt und die Schülerantworten dann automatisch nach diesen Unit-Tests kontrolliert werden.