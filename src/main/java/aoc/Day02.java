package aoc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day02 implements Day{

    private final Map<String, Integer> config = Map.of("red", 12, "green", 13, "blue", 14);
    @Override
    public String part1(List<String> input) {
        int games = 0;
        for (String line: input) {
            String[] record = line.split(":");
            String [] sets = record[1].split(";");
            boolean isValid = true;
            for (String set: sets) {
                String[] cubes = set.split(",");
                for (String cub: cubes) {
                    String[] cubHistory = cub.split(" ");
                    if (Integer.parseInt(cubHistory[1]) > config.get(cubHistory[2])) {
                        isValid = false;
                        break;
                    }
                }

                if (!isValid) {
                    break;
                }
            }
            if (isValid) {
                games += Integer.parseInt(record[0].split(" ")[1]);
            }
        }
        return String.valueOf(games);
    }

    @Override
    public String part2(List<String> input) {
        int sumOfPowers = 0;
        HashMap<String, Integer> nbrCub = new HashMap<>();
        for (String line: input) {
            nbrCub.clear();
            String[] record = line.split(":");
            String [] sets = record[1].split(";");
            for (String set: sets) {
                String[] cubes = set.split(",");
                for (String cub: cubes) {
                    String[] cubHistory = cub.split(" ");
                    if (nbrCub.containsKey(cubHistory[2])) {
                        int currentFewestNbr = nbrCub.get(cubHistory[2]);
                        if (currentFewestNbr < Integer.parseInt(cubHistory[1])) {
                            nbrCub.put(cubHistory[2], Integer.valueOf(cubHistory[1]));
                        }
                    } else {
                        nbrCub.put(cubHistory[2], Integer.valueOf(cubHistory[1]));
                    }
                }
            }

            int power = 1;
            for (Map.Entry<String, Integer> set: nbrCub.entrySet()){
                power *= set.getValue();
            }

            sumOfPowers += power;
        }
        return String.valueOf(sumOfPowers);
    }
}
