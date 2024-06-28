package exercise;

import java.util.Arrays;

// BEGIN
public final class MaxThread extends Thread {

    private int[] numbers;

    private int max;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int getMax() {
        return max; 
    }

    @Override
    public void run() {
        max = Arrays.stream(numbers).max().getAsInt();
    }

}
// END
