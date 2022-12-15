package practice;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    static List<Card> deck = new ArrayList<>();
    public static void main(String[] args) {
        GenerateOrderedDeck();
    }

    static void GenerateOrderedDeck() {
        for (String shape : Card.SHAPES) {
            for (String name : Card.NAMES) {
                System.out.printf("%s of %s%n", name, shape);
            }
        }
    }

    static void GenerateRandomDeck() {
        Integer i = 1;
        while (deck.size() < (Card.SHAPES.size() * Card.VALUES.size())) {
            Card card = Card.GetRandomCard();
            deck.add(card);
            System.out.printf("%d -> %s%n", i, card);
            i++;
        }

    }
}
