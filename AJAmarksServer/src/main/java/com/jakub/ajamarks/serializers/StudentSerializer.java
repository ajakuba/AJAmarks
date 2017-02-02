package com.jakub.ajamarks.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StudentSerializer extends JsonSerializer<Student> {
    @Override
    public void serialize(Student student, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("userName", student.getUserName());
        jsonGenerator.writeObjectField("firstName", student.getFirstName());
        jsonGenerator.writeObjectField("lastName", student.getLastName());
        jsonGenerator.writeObjectField("classroom", getStudentClassroom(student.getClassroom()));
        jsonGenerator.writeObjectField("studentsMarks", getmarkValueList(student));
        jsonGenerator.writeEndObject();
    }

    private Classroom getStudentClassroom(Classroom classroom){
        Classroom simpleClassroom = new Classroom();
        simpleClassroom.setClassroomName(classroom.getClassroomName());
        simpleClassroom.setClassroomNumber(classroom.getClassroomNumber());
        simpleClassroom.setStudentsInClassroom(Collections.emptySet());

        return simpleClassroom;
    }


    private List<Integer> getmarkValueList(Student student) {
        List<Mark> studentMarks = student.getStudentMarks();
        return studentMarks.stream().map(mark -> mark.getMarkValue()).collect(Collectors.toList());
    }

}
