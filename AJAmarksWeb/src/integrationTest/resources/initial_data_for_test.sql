insert into Classroom (idClassroom, classroomname, classroomNumber) values (101, 'Pierwsza', 11);

insert into Subject (subjectId, subjectName) values (101, 'Matematyka');

insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (101, 'Jakub', 'Anioł', 'JA', 1);

insert into Mark (idMark, markValue, markName) values (101, 1, 'NDST');

insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (101,1);
