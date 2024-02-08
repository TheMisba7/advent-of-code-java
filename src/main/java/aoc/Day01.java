package aoc;

import java.util.List;


public class Day01 implements Day {
    @Override
    public String part1(List<String> input) {
        String first;
        String last;
        int sum = 0;
        for (String line: input) {
            first = null; last = null;
            for (char c: line.toCharArray()) {
                if (Character.isDigit(c)) {
                    if (first == null) {
                        first = String.valueOf(c);
                        last = first;
                    } else {
                        last = String.valueOf(c);
                    }
                }
            }
            if (first != null && last != null) {
                sum += Integer.parseInt(first + last);
            }
        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(List<String> input) {
        String first;
        String last;
        int sum = 0;
        for (String line: input) {
            first = null;
            last = null;
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))){
                    if (first == null){
                        first = String.valueOf(line.charAt(i));
                        last = first;
                    } else
                        last = String.valueOf(line.charAt(i));
                } else {
                    var restOfLine = line.substring(i);
                    for (Number nbr: Number.values()) {
                        if (restOfLine.startsWith(nbr.name())) {
                            if (first == null){
                                first = String.valueOf(nbr.value);
                                last = first;
                            } else
                                last = String.valueOf(nbr.value);
                            break;
                        }
                    }
                }
            }
            if (first != null && last != null) {
                sum += Integer.parseInt(first+last);
            }
        }
        return String.valueOf(sum);
    }
        enum Number {
            one(1), two(2), three(3), four(4), five(5),
            six(6), seven(7), eight(8), nine(9);
            final int value;
            Number(int number) {
                this.value = number;
            }
     }
}
