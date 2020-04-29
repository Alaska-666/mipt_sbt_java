package hw_reflection;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class JsonSerializedStringBuilderTest {

    @org.junit.jupiter.api.Test
    void testToString() {
        String json = "{\n" +
                "  \"Person\": {\n" +
                "    \"firstName\": \"Andrey\",\n" +
                "    \"lastName\": \"Json\",\n" +
                "    \"Address\": {\n" +
                "      \"city\": \"Moscow\",\n" +
                "      \"postalCode\": \"911\"\n" +
                "    },\n" +
                "    \"phoneNumbers\": [\n" +
                "      \"123-1234-523\",\n" +
                "      \"432-23-232-23\"\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        Serializer serializer = new TreeSerializer(new JsonSerializedStringBuilder());
        String serialize = serializer.serialize(new Person("Andrey", "Json",
                new Address("Moscow", "911"), Arrays.asList("123-1234-523", "432-23-232-23")));
        assertEquals(json, serialize);
    }
}