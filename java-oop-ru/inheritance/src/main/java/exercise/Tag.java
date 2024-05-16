package exercise;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {

    protected String name;

    protected LinkedHashMap<String, String> attributes;

    protected Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = new LinkedHashMap<>(attributes);
    }

    public String toString() {
        return tagStart() + tagEnd();
    }

    protected String tagStart() {
        return "<" + name + attributesToString() + ">";
    }

    protected String tagEnd() {
        return "</" + name + ">";
    }

    protected String attributesToString() {
        if (attributes.isEmpty()) {
            return "";
        }

        return attributes.entrySet().stream()
            .map(this::tagToString)
            .collect(Collectors.joining(" ", " ", ""));
    }

    protected String tagToString(Map.Entry<String, String> entry) {
        return "%s=\"%s\"".formatted(entry.getKey(), entry.getValue());
    }

}
// END
