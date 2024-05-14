package exercise;

import java.util.Objects;

// BEGIN
public final class ReversedSequence implements CharSequence {

    private final String sequence;

    public ReversedSequence(String sequence) {
        var safeSequence = Objects.requireNonNull(sequence);
        this.sequence = new StringBuilder(safeSequence).reverse().toString();
    }

    @Override
    public int length() {
        return sequence.length();
    }

    @Override
    public char charAt(int index) {
        return sequence.charAt(index);
    }

    @Override
    public boolean isEmpty() {
        return CharSequence.super.isEmpty();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return sequence.subSequence(start, end);
    }

    @Override
    public String toString() {
        return sequence;
    }

}
// END
