package exercise;

import java.util.List;
import java.util.stream.IntStream;

// BEGIN
public class ReversedSequence implements CharSequence {

    private String string;

    public ReversedSequence(String string){
        this.string = string;
    }


    public int length() {
        return string.length();
    }

    public char charAt(int index) {
        return string.toString().charAt(index);
    }

    public boolean isEmpty() {
        return string.isEmpty();
    }

    public CharSequence subSequence(int start, int end) {
        return null;
    }

    public IntStream chars() {
        return CharSequence.super.chars();
    }

    public IntStream codePoints() {
        return CharSequence.super.codePoints();
    }

    @Override
    public String toString() {
        StringBuilder meow = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            meow.append(charAt(string.length()-i-1));
        }
        return meow.toString();
    }


}
// END
