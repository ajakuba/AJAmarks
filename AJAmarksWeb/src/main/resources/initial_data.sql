insert into Classroom (idClassroom, classroomname, classroomNumber) values (1, 'Pierwsza', 11);
insert into Classroom (idClassroom, classroomname, classroomNumber) values (2, 'Druga', 22);
insert into Classroom (idClassroom, classroomname, classroomNumber) values (3, 'Trzecia', 33);
insert into Classroom (idClassroom, classroomname, classroomNumber) values (4, 'Czwarta', 44);
insert into Classroom (idClassroom, classroomname, classroomNumber) values (5, 'Piąta', 55);
insert into Classroom (idClassroom, classroomname, classroomNumber) values (6, 'Szósta', 66);
insert into Classroom (idClassroom, classroomname, classroomNumber) values (7, 'Siódma', 77);
insert into Classroom (idClassroom, classroomname, classroomNumber) values (8, 'Ósma', 88);

insert into Subject (subjectId, subjectName) values (101, 'Matematyka');
insert into Subject (subjectId, subjectName) values (102, 'Historia');

insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (1, 'Jakub', 'Anioł', 'JA', 1);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (2, 'Bogdan', 'Roczny', 'BR', 2);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (3, 'Cecyla', 'Nowakowska', 'CN', 3);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (4, 'Danuta', 'Smiala', 'DS', 4);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (5, 'Edward', 'Nikło', 'EN', 5);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (6, 'Filon', 'Niebieski', 'FN', 6);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (7, 'Grażyna', 'Astra', 'GA', 7);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (9, 'Ignacy', 'Siemaszko', 'IS', 1);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (8, 'Hubert', 'Niestały', 'HN', 8);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (10, 'Jolanta', 'Balut', 'JB', 2);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (11, 'Krystyna', 'Okotowska', 'KO', 3);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (12, 'Ludmiła', 'Radowska', 'LR', 4);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (13, 'Łukasz', 'Długi', 'ŁD', 5);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (14, 'Magdalena', 'Dystyng', 'MD', 6);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (15, 'Norbert', 'Kłos', 'NK', 7);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (16, 'Olek', 'Szybki', 'OS', 8);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (17, 'Piotr', 'Entrant', 'PE', 1);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (18, 'Robert', 'Dmos', 'RD', 2);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (19, 'Sławomir', 'Festowski', 'SF', 3);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (20, 'Tomasz', 'Zambina', 'TZ', 4);
insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (21, 'Urszula', 'Tarasko', 'UT', 5);

insert into Mark (idMark, markValue, markName) values (1, 1, 'NDST');
insert into Mark (idMark, markValue, markName) values (2, 2, 'MRN');
insert into Mark (idMark, markValue, markName) values (3, 3, 'DST');
insert into Mark (idMark, markValue, markName) values (4, 4, 'DB');
insert into Mark (idMark, markValue, markName) values (5, 5, 'BDB');
insert into Mark (idMark, markValue, markName) values (6, 6, 'CEL');

insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (1,2);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (1,6);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (2,3);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (2,5);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (2,7);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (2,9);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (2,11);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (3,4);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (3,5);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (3,6);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (3,7);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (3,8);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (4,5);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (4,5);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (4,6);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (4,8);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (4,11);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (5,12);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (5,13);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (5,13);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (5,15);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (5,16);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (6,1);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (6,1);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (6,17);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (6,18);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (6,19);
insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (6,21);





