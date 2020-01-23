package api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.encoding.soap.DeserializationException;

import java.io.IOException;
import java.io.InputStream;

public class JsonHelper {

    private final ObjectMapper objectMapper;

    public JsonHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T readValue(final InputStream source, final Class<T> valueType) {
        try {
            return objectMapper.readValue(source, valueType);
        } catch (IOException e) {
            throw new DeserializationException(e.getMessage(), e);
        }
    }

}
