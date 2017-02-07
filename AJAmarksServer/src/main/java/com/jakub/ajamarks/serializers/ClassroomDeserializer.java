package com.jakub.ajamarks.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jakub.ajamarks.entities.Classroom;

import java.io.IOException;

/**
 * Created by ja on 05.02.17.
 */
public class ClassroomDeserializer extends JsonDeserializer<Classroom> {
    @Override
    public Classroom deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Classroom classroom = new Classroom();
        classroom.setClassroomName(jsonParser.getText());
        classroom.setClassroomNumber(jsonParser.getIntValue());

        return classroom;
    }
}
