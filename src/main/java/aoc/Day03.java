package aoc;

import java.util.List;

public class Day03 implements Day{
    @Override
    public String part1(List<String> input) {

        int partNumbers = 0;
        for (int i = 0; i < input.size(); i++) {

            String line = input.get(i);
            PartNumber partNumber = null;
            for (int j = 0; j < line.length(); j++) {
                if (Character.isDigit(line.charAt(j))) {
                    if (partNumber == null) {
                        partNumber = new PartNumber(i, j, line.charAt(j));
                    } else {
                        partNumber.add(line.charAt(j), j);
                    }
                } else {
                    if (partNumber != null) {
                        int startIndex = partNumber.startIndex > 1 ? partNumber.startIndex - 1 : 0;
                        int startLine = i > 1 ? i - 1 : 0;
                        int nextLine = input.size() > partNumber.line + 1 ? partNumber.line + 1: (partNumber.line > 0 ? partNumber.line - 1 : partNumber.line);
                        System.out.println(partNumber.toString());
                        if (isAdjacent(input.get(startLine), startIndex, partNumber.lastIndex + 1)) {
                            System.out.println("found: "+partNumber.parts);
                            System.out.println("at line: "+input.get(startLine));
                            partNumbers += partNumber.getAsInt();
                            partNumber = null;
                        } else if (isAdjacent(input.get(partNumber.line), startIndex, partNumber.lastIndex + 1)) {
                            System.out.println("found: "+partNumber.parts);
                            System.out.println("at line: " + line);
                            partNumbers += partNumber.getAsInt();
                            partNumber = null;
                        } else if (isAdjacent(input.get(nextLine), startIndex, partNumber.lastIndex + 1)) {
                            System.out.println("found: "+partNumber.parts);
                            System.out.println("at line: "+input.get(nextLine));
                            partNumbers += partNumber.getAsInt();
                            partNumber = null;
                        } else {
                            System.out.println("not part number: "+partNumber.parts);
                            System.out.println("at line: "+input.get(partNumber.line));
                            partNumber = null;
                        }
                    }
                }
            }
        }
        return String.valueOf(partNumbers);
    }

    private boolean isAdjacent(String line, int startIndex, int lastIndex) {
        System.out.println(line + " : " + line.length());
        System.out.println(startIndex + "/" + lastIndex);
        String rangeToCheck = line.substring(startIndex, line.length() > lastIndex ? ++lastIndex: lastIndex);
        System.out.println("line to check : " + rangeToCheck);
        for (char c : rangeToCheck.toCharArray()) {
            if (!Character.isDigit(c) && c !='.')
                return true;
        }
        return false;
    }

    @Override
    public String part2(List<String> input) {
        return null;
    }

    private final class PartNumber {
        private int line;
        private int startIndex;
        private int lastIndex;
        private String parts;

        public PartNumber(int line, int startIndex, char firstPart) {
            this.line = line;
            this.startIndex = startIndex;
            this.lastIndex = startIndex;
            this.parts = String.valueOf(firstPart);
        }

        public void add(char c, int index) {
            this.parts += c;
            this.lastIndex = index;
        }

        public int getAsInt() {
            return Integer.parseInt(parts);
        }

        @Override
        public String toString() {
            return "line number: " + line + " / start index: " + startIndex + " / lastindex: " + lastIndex + " / parts: " + parts;
        }
    }
}
