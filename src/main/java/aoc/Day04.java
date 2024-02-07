package aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day04 implements Day{
    @Override
    public String part1(List<String> input) {
        int points = 0;
        for (String line: input) {
            Card card = Card.parseString(line);
            int cardPoints = 0;
            for (Integer nbr: card.winningNumbers) {
                if (card.availableNumbers.contains(nbr)) {
                    cardPoints = cardPoints == 0 ? 1 : cardPoints * 2;
                }
            }
            points += cardPoints;
        }
        return String.valueOf(points);
    }

    @Override
    public String part2(List<String> input) {
        List<Card> cardList = new ArrayList<>();
        for (String line: input) {
            Card card = Card.parseString(line);
            cardList.add(card);
        }
        int cardsNbr = 0;
        for (int i = 0; i < cardList.size(); i++) {
            int count = cardList.get(i).countMatchingNumbers();
            int next = i + 1;
            while (count > 0 && next < cardList.size()) {
                cardList.get(next).count += cardList.get(i).count;
                count--;
                next++;
            }
            cardsNbr += cardList.get(i).count;
        }
        return String.valueOf(cardsNbr);
    }
    private static final class Card {
        int id;
        Set<Integer> winningNumbers;
        Set<Integer> availableNumbers;
        int count;

        private Card(int id, Set<Integer> winningNumbers, Set<Integer> availableNumbers, int count) {
            this.id = id;
            this.winningNumbers = winningNumbers;
            this.availableNumbers = availableNumbers;
            this.count = count;
        }

        public int countMatchingNumbers() {
            int nbr = 0;
            for (Integer i: winningNumbers) {
                if (availableNumbers.contains(i))
                    nbr++;
            }
            return nbr;
        }

        public static Card parseString(final String line) {
            String[] card_numbers = line.split(":");
            String[] cardNameAndId = card_numbers[0].split("\\s+");
            int cardId = Integer.parseInt(cardNameAndId[1]);
            String[] numbers = card_numbers[1].split("\\|");
            Set<Integer> winning_numbers = Arrays.stream(numbers[0].split("\\s+"))
                    .filter(s -> !s.isBlank())
                    .mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet());
            Set<Integer> available_numbers = Arrays.stream(numbers[1].split("\\s+"))
                    .filter(s -> !s.isBlank())
                    .mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet());

            return new Card(cardId, winning_numbers, available_numbers, 1);
        }
    }
}
