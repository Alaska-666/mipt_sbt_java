package hw_reflection;

import java.util.List;

public interface SerializedStringBuilder {
    void setValue(String key, String value);
    void setArray(String key, List<String> array);
    void startLevel(String name);
    void endLevel();

    String toString();
}
