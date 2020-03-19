package theme4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Desk {
    private final int size = 52;
    private final char[] colours = {'♥', '♦', '♣', '♠'};
    private final static String[] SUITS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private final static int[] RANK = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

    private List<Card> cards;

    Desk() {
        this.cards = new ArrayList<>();
        for (char symbol: colours
             ) {
            for (int i = 0; i < SUITS.length ; i++) {
                cards.add(new Card(RANK[i], SUITS[i], symbol));
            }
        }

    }

    Card getCard() {
        Card card = null;
        if (!this.cards.isEmpty()) {
            card = this.cards.remove(0);
        }
        return card;
    }

    int getAmountCards(){
        return this.cards.size();
    }

    void shuffleDesk(){
        Collections.shuffle(this.cards);

    }

    /*public static void main(String[] args) {
        Desk desk = new Desk();
        desk.shuffleDesk();
        for (Card cards: desk.cards
             ) {
            System.out.println(cards.toString());
        }
        Player player = new Player("Dimon", 21, desk);
        System.out.println(player.getPoints());
    }*/

    @Override
    public String toString() {
        return "Desk{" +
                "colours=" + Arrays.toString(colours) +
                ", cards=" + cards +
                '}';
    }
}
