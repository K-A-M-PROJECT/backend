package com.kam.userManagement.config.NameSerialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import javax.naming.Name;
import java.io.IOException;

public class NameSerializer extends JsonSerializer<Name> {

    @Override
    public void serialize(Name name, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(name.toString());
    }
}