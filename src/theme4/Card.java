package theme4;

public class Card {
    private int rank;
    private String suit;
    private char colour;

    public Card(int rank, String suit, char colour) {
        this.rank = rank;
        this.suit = suit;
        this.colour = colour;
    }

    int getRank() {
        return rank;
    }

    String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return this.colour + this.suit;
    }


}

