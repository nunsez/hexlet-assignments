package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    protected String body;

    protected ArrayList<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = new ArrayList<>(children);
    }

    public String toString() {
        var childrenString = children.stream()
            .map(Tag::toString)
            .collect(Collectors.joining());

        return tagStart() + body + childrenString + tagEnd();
    }

}
// END
