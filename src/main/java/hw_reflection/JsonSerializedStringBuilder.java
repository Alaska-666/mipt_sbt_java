package hw_reflection;

import java.util.List;

public class JsonSerializedStringBuilder implements SerializedStringBuilder {
    private Ident identer = new Ident("  ", 1);
    private StringBuilder json = new StringBuilder();


    @Override
    public void setValue(String key, String value) {
        openJsonlTag(key);
        addValue(value);
        json.append(",\n");
    }

    @Override
    public void setArray(String key, List<String> array) {
        openJsonlTag(key);
        json.append("[\n");
        identer.inc();
        int counter = 1;
        for (String item : array) {
            addValueIdent(item);
            if (counter != array.size()) {
                json.append(',');
            }
            json.append('\n');
            counter++;
        }
        identer.decr();
        json.append(identer.ident()).append("],\n");
    }

    @Override
    public void startLevel(String name) {
        openJsonlTag(name);
        json.append("{\n");
        identer.inc();
    }

    @Override
    public void endLevel() {
        int lastIndex = json.lastIndexOf(",");
        if (lastIndex >= 0) {
            json.replace(lastIndex, lastIndex + 1, "");
        }
        identer.decr();
        json.append(identer.ident()).append("},\n");
    }

    @Override
    public String toString() {
        json.replace(json.lastIndexOf(","), json.length(), "");
        return "{\n" + json.toString() + "\n}";
    }

    private void openJsonlTag(String tag) {
        addValueIdent(tag);
        json.append(": ");
    }

    private void addValue(String value) {
        json.append('"').append(value).append('"');
    }

    private void addValueIdent(String value) {
        json.append(identer.ident());
        addValue(value);
    }
}
