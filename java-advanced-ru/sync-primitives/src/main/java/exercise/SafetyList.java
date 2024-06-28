package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    // BEGIN

    private List<Integer> numbers = new ArrayList<>();

    public synchronized void add(int number) {
        numbers.add(number);
    }

    public Integer get(int index) {
        return numbers.get(index);
    }

    public int getSize() {
        return numbers.size();
    }
    // END
}
