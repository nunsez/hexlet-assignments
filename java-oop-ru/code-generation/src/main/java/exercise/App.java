package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public final class App {

    public static void save(Path path, Car car) throws IOException {
        var json = car.serialize();
        Files.writeString(path, json);
    }

    public static Car extract(Path path) throws IOException {
        var json = Files.readString(path);
        return Car.unserialize(json);
    }

}
// END
