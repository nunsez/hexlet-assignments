package exercise;

import java.util.List;

// BEGIN
public final class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int count) {
        return apartments.stream()
            .sorted()
            .limit(count)
            .map(String::valueOf)
            .toList();
    }

}
// END
