package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public final class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        var map = storage.toMap();

        map.keySet().forEach(storage::unset);
        map.forEach((key, value) -> storage.set(value, key));
    }

}
// END
