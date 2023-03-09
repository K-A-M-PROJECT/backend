package com.kam.userManagement.config.NameSerialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.ldap.LdapName;
import java.io.IOException;

public class NameDeserializer extends JsonDeserializer<Name> {

    @Override
    public Name deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        try {
            return new LdapName(jsonParser.getValueAsString());
        } catch (InvalidNameException e) {
            throw new IOException("Invalid DN: " + jsonParser.getValueAsString(), e);
        }
    }
}
