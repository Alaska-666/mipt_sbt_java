package hw_reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TreeSerializer implements Serializer {
    private SerializedStringBuilder builder;

    TreeSerializer(SerializedStringBuilder b) {
        builder = b;
    }

    private boolean isPrimitiveOrString(Field field) {
        return isPrimitiveOrString(field.getType());
    }

    private boolean isPrimitiveOrString(Class clazz) {
        return clazz.isPrimitive() || clazz.equals(String.class);
    }

    private boolean isCollection(Field field) {
        return Collection.class.isAssignableFrom(field.getType());
    }

    private void recSerialize(Object object) throws IllegalAccessException{
        builder.startLevel(object.getClass().getSimpleName());
        for (Field declaredField : object.getClass().getDeclaredFields()) {
            String fieldName = declaredField.getName();
            declaredField.setAccessible(true);
            Object value = declaredField.get(object);
            if (isPrimitiveOrString(declaredField)) {
                builder.setValue(fieldName, value.toString());
            } else if (isCollection(declaredField)) {
                List<String> values = new ArrayList<>();
                for (Object o : (Iterable) value) {
                    values.add(o.toString());
                }
                builder.setArray(fieldName, values);
            } else {
                recSerialize(value);
            }
        }
        builder.endLevel();
    }

    @Override
    public String serialize(Object object) {
        try {
            recSerialize(object);
        } catch (IllegalAccessException ignored) {}
        return builder.toString();
    }
}
