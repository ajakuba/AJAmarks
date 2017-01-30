package com.jakub.ajamarks.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jakub.ajamarks.entities.Classroom;
import com.jakub.ajamarks.entities.Student;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassroomSerializer extends JsonSerializer<Classroom> {
    @Override
    public void serialize(Classroom classroom, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("classromName", classroom.getClassroomName());
        jsonGenerator.writeObjectField("classromNumber", classroom.getClassroomNumber());
        jsonGenerator.writeObjectField("studentsInClassroom", getStudentsFirstAndLastName(classroom));
        jsonGenerator.writeEndObject();
    }

    private List<String> getStudentsFirstAndLastName(Classroom classroom){
        Set<Student> studentsInClassroom = classroom.getStudentsInClassroom();
        List<String> studentsFirstAndLastNameList = studentsInClassroom.stream().map(student -> {
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            String studentName = firstName + " " + lastName;
            return studentName;
        }).collect(Collectors.toList());

        return studentsFirstAndLastNameList;
    }
}
