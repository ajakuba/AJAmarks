package com.jakub.ajamarks.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jakub.ajamarks.entities.Mark;
import com.jakub.ajamarks.entities.Student;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MarkSerializer extends JsonSerializer<Mark> {

    @Override
    public void serialize(Mark mark, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("markName", mark.getMarkName());
        jsonGenerator.writeObjectField("markValue", mark.getMarkValue());
        jsonGenerator.writeObjectField("studentsWithMark", getStudentsFirstAndLastName(mark));
        jsonGenerator.writeEndObject();

    }

    private List<String> getStudentsFirstAndLastName(Mark mark) {
        Set<Student> studentsWithMark = mark.getStudentsWithMark();
        return studentsWithMark.stream().map(student -> {
            String firstName = student.getFirstName();
            String lastName = student.getLastName();
            return firstName.concat(lastName);
        }).collect(Collectors.toList());
    }
}
