package practice;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class Card {
    // Constants
    static final List<String> SHAPES = List.of("Spade", "Club", "Heart", "Diamond");
    static final List<String> NAMES = List.of("A", "2", "3", "4",
            "5", "6", "7", "8",
            "9", "10", "J", "Q", "K");
    static final List<Integer> VALUES = List.of(1,2,3,4,5,6,7,8,9,10,10,10,10);

    private final String name;
    private final String shape;

    public Card(String name, String shape) {
        this.name = name;
        this.shape = shape;
    }

    @Override
    public boolean equals(Object obj) {
        Card anotherCard = (Card) obj;
        if (name.equals(anotherCard.getname()) &&
                shape.equals(anotherCard.getShape())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "%s of %s".formatted(name, shape);
    }

    public static Card GetRandomCard() {
        Random rand = new SecureRandom();
        Integer randname = rand.nextInt(NAMES.size());
        Integer randShape = rand.nextInt(SHAPES.size());

        return new Card(NAMES.get(randname), SHAPES.get(randShape));
    }

    public String getname() {
        return name;
    }

    public String getShape() {
        return shape;
    }

    public Integer getValue() {
        return VALUES.get(NAMES.indexOf(name));
    }

}
