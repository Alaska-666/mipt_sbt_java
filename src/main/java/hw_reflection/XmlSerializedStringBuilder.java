package hw_reflection;

import java.util.List;
import java.util.Stack;

public class XmlSerializedStringBuilder implements SerializedStringBuilder {
    private StringBuilder xml = new StringBuilder();
    private Stack<String> curLevelName = new Stack<>();
    private Ident identer = new Ident("  ");

    @Override
    public void setValue(String key, String value) {
        openXmlTag(key);
        xml.append(value);
        closeXmlSingleLine(key);
    }

    @Override
    public void setArray(String key, List<String> array) {
        startLevel(key);
        int counter = 1;
        for (String item : array) {
            setValue(Integer.toString(counter), item);
            counter++;
        }
        endLevel();
    }

    @Override
    public void startLevel(String name) {
        openXmlTagWithBreakLine(name);
        curLevelName.push(name);
        identer.inc();
    }

    @Override
    public void endLevel() {
        identer.decr();
        String name = curLevelName.pop();
        closeXmlTagWithBreakLine(name);
    }

    @Override
    public String toString() {
        return xml.toString();
    }

    private void openXmlTag(String tag) {
        xml.append(identer.ident()).append('<').append(tag).append('>');
    }

    private void openXmlTagWithBreakLine(String tag) {
        openXmlTag(tag);
        xml.append("\n");
    }

    private void closeXmlSingleLine(String tag) {
        xml.append("</").append(tag).append(">").append("\n");
    }

    private void closeXmlTagWithBreakLine(String tag) {
        xml.append(identer.ident());
        closeXmlSingleLine(tag);
    }
}
