package hw_reflection;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Serializer serializer = new TreeSerializer(new XmlSerializedStringBuilder());
        String serialize = serializer.serialize(new Person("Andrey", "Xml",
                new Address("Moscow", "911"), Arrays.asList("123-1234-523", "432-23-232-23")));
        System.out.println(serialize);
    }
}
