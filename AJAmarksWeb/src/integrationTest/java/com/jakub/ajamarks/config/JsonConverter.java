package com.jakub.ajamarks.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;

/**
 * Created by ja on 05.02.17.
 */
public class JsonConverter {
    public static String convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper ow = new ObjectMapper();
        ow.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        String json = ow.writeValueAsString(object);
        System.out.println(json);
        return json;




    }


}
