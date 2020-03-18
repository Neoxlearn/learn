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

    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return this.colour + this.suit;
    }


}

