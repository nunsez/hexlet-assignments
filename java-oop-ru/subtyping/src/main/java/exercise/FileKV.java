package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public final class FileKV implements KeyValueStorage {

    private final String filePath;

    private final HashMap<String, String> data;

    public FileKV(String filePath, Map<String, String> data) {
        this.filePath = filePath;
        this.data = new HashMap<>(data);
    }

    @Override
    public void set(String key, String value) {
        data.put(key, value);
        dump();
    }

    @Override
    public void unset(String key) {
        data.remove(key);
        dump();
    }

    @Override
    public String get(String key, String defaultValue) {
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(data);
    }

    private void dump() {
        Utils.writeFile(filePath, Utils.serialize(data));
    }

}
// END
