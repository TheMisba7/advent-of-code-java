package aoc;

import java.util.List;

public class Day03 implements Day{
    @Override
    public String part1(List<String> input) {

        long startTime = System.currentTimeMillis();
        int partNumbers = 0;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            int length = line.length();
            PartNumber partNumber = null;
            for (int j = 0; j < length; j++) {
                if (Character.isDigit(line.charAt(j))) {
                    if (partNumber == null) {
                        partNumber = new PartNumber(i, j, line.charAt(j));
                    } else {
                        partNumber.add(line.charAt(j), j);
                    }
                } else {
                    if (partNumber != null) {
                        if (partNumber.isPartNumber(i, input))
                            partNumbers += partNumber.getAsInt();
                        partNumber = null;

                    }
                }
            }
            if (partNumber != null) {
                if (partNumber.isPartNumber(i, input))
                    partNumbers += partNumber.getAsInt();
                partNumber = null;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("process time: " + (endTime - startTime));
        return String.valueOf(partNumbers);
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

        public boolean isPartNumber(int i, List<String> input) {
            int startIndex = this.startIndex > 1 ? this.startIndex - 1 : 0;
            int startLine = i > 1 ? i - 1 : 0;
            int nextLine = input.size() > this.line + 1 ? this.line + 1: (this.line > 0 ? this.line - 1 : this.line);
            if (isAdjacent(input.get(startLine), startIndex, this.lastIndex + 1)) {
                return true;
            } else if (isAdjacent(input.get(this.line), startIndex, this.lastIndex + 1)) {
                return true;
            } else return isAdjacent(input.get(nextLine), startIndex, this.lastIndex + 1);
        }

        private boolean isAdjacent(String line, int startIndex, int lastIndex) {
            String rangeToCheck = line.substring(startIndex, line.length() > lastIndex ? ++lastIndex: lastIndex);
            for (char c : rangeToCheck.toCharArray()) {
                if (!Character.isDigit(c) && c !='.')
                    return true;
            }
            return false;
        }
        public int getAsInt() {
            return Integer.parseInt(parts);
        }
    }
}
