package hw_reflection;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class XmlSerializedStringBuilderTest {
    @org.junit.jupiter.api.Test
    void testToString() {
        String xml = "<Person>\n" +
                "  <firstName>Andrey</firstName>\n" +
                "  <lastName>Xml</lastName>\n" +
                "  <Address>\n" +
                "    <city>Moscow</city>\n" +
                "    <postalCode>911</postalCode>\n" +
                "  </Address>\n" +
                "  <phoneNumbers>\n" +
                "    <1>123-1234-523</1>\n" +
                "    <2>432-23-232-23</2>\n" +
                "  </phoneNumbers>\n" +
                "</Person>\n";
        Serializer serializer = new TreeSerializer(new XmlSerializedStringBuilder());
        String serialize = serializer.serialize(new Person("Andrey", "Xml",
                new Address("Moscow", "911"), Arrays.asList("123-1234-523", "432-23-232-23")));
        assertEquals(xml, serialize);
    }
}