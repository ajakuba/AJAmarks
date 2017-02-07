insert into Classroom (idClassroom, classroomname, classroomNumber) values (101, 'Default', 111);

insert into Subject (subjectId, subjectName) values (101, 'Default');

insert into Student (idStudent, firstName, lastName, userName, classroom_idClassroom) values (101, 'DefaultFirst', 'DefaultLast', 'DefaultuserName', 101);

insert into Mark (idMark, markValue, markName) values (101, 111, 'Default');

insert into Mark_Student (studentMarks_idMark, studentsWithMark_idStudent) values (101,101);