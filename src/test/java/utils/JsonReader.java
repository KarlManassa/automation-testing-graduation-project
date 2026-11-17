package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JsonReader {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final JsonNode testData;

    static {
        try (InputStream inputStream = JsonReader.class
                .getClassLoader()
                .getResourceAsStream("testdata.json")) {

            if (inputStream == null) {
                throw new RuntimeException("testdata.json not found");
            }

            testData = mapper.readTree(inputStream);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read testdata.json", e);
        }
    }

    public static JsonNode getData(String section) {
        JsonNode data = testData.get(section);

        if (data == null) {
            throw new RuntimeException("Test data section not found: " + section);
        }

        return data;
    }

    public static String getValue(String section, String field) {
        JsonNode data = getData(section);
        JsonNode value = data.get(field);

        if (value == null || value.isNull()) {
            throw new RuntimeException(
                    "Test data field not found: " + section + "." + field
            );
        }

        return value.asText();
    }
}
