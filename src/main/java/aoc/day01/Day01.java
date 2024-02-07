package aoc.day01;

import aoc.Day;

import java.util.Arrays;
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
            String result = null;
            StringBuilder stringBuilder = new StringBuilder(5);
            first = null; last = null;
            for (char c : line.toCharArray()) {
                if (Character.isDigit(c)) {
                    stringBuilder.delete(0, stringBuilder.length());
                    if (first == null) {
                        first = String.valueOf(c);
                        last = first;
                    } else {
                        last = String.valueOf(c);
                    }
                } else {
                    stringBuilder.append(c);
                    if (stringBuilder.length() >= 3) {
                        String str = stringBuilder.toString();
                        Number digit = Arrays.stream(Number.values())
                                .filter(n -> str.startsWith(n.name()))
                                .findFirst()
                                .orElse(null);
                        for (Number number: Number.values()) {
                            if (str.startsWith(number.name())) {
                                digit = number;
                                stringBuilder.delete(0, digit.name().length());
                                break;
                            } else if (str.contains(number.name())) {
                                digit = number;
                                stringBuilder.delete(0, stringBuilder.length());
                                break;
                            }
                        }

                        if (digit != null) {
                            if (first == null) {
                                first = String.valueOf(digit.value);
                                last = first;
                            } else {
                                last = String.valueOf(digit.value);
                            }
                        }
                        if (stringBuilder.length() >=5) {
                            stringBuilder.deleteCharAt(0);
                        }
                    }
                }
            }

            System.out.println(line);
            if (first != null && last != null) {
                result = first + last;
                System.out.println(result);
                sum += Integer.parseInt(result);
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
