package exercise;

import java.util.Arrays;

// BEGIN
public final class MinThread extends Thread {

    private int[] numbers;

    private int min;

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMin() {
        return min;
    }

    @Override
    public void run() {
        min = Arrays.stream(numbers).min().getAsInt();
    }

}
// END
