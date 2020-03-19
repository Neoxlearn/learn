package theme4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Player {
    private String name;
    private int win;
    private int lose;
    private boolean stop;
    private List<Card> hand;
    private Desk desk;
    private boolean isNpc;
    private int scoreForMove;

    private final int maxScore;

    public Player(String name, int maxScore, Desk desk) {
        this.name = name;
        this.maxScore = maxScore;
        this.desk = desk;
        this.hand = new ArrayList<>(makeHand(desk));
        this.win = 0;
        this.lose = 0;
        this.isNpc = false;
        this.stop = false;
    }

    public Player(String name, int maxScore, int scoreForMove , Desk desk, boolean isNpc){
        this(name, maxScore, desk);
        this.scoreForMove = scoreForMove;
        this.isNpc = isNpc;
    }
    protected int getWin(){
        return this.win;
    }

    protected String getName(){
        return this.name;
    }

    void incWin(){
        this.win ++;
    }
    void incLose(){
        this.lose ++;
    }

    boolean move() {
        return this.isNpc ? npcMove() : isMove();
    }



    private ArrayList<Card> makeHand(Desk desk){
        ArrayList<Card> newHand = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            newHand.add(desk.getCard());
        }

        return newHand;

    }

    void setNewHand(){
        this.hand.clear();
        for (int i = 0; i < 2; i++) {
            this.hand.add(this.desk.getCard());
        }
    }

    int getPoints(){
        int points = 0;
        for (Card card: this.hand
             ) {
            points += card.getRank();
        }

        return points;
    }

    private void getCard() {
        this.hand.add(this.desk.getCard());
    }

    boolean isLose(){
        return this.getPoints() > this.maxScore;
    }

    void upadate(){
        this.stop = false;
    }

    private boolean isMove() {
        boolean result;

        Scanner scanner = new Scanner(System.in);
        if (result = !isLose()){
            if(result = !this.stop){
                System.out.print("Еще одну карту?[y/n]: ");
                String answer = scanner.nextLine().trim().toLowerCase();
                if ("y".equals(answer)) {
                    System.out.printf("Игрок [%s] берет карту..\n", this.name);
                    this.getCard();
                    System.out.println(this.getPlayerHand());
                } else {
                    this.stop = true;

                }
            }
        }
        //scanner.close();
        return result;
    }

    private boolean npcMove(){
        boolean result;
        if(result = !isLose()){
           if (result = (this.getPoints() <= this.scoreForMove)){
            System.out.printf("Игрок [%s] берет карту..\n", this.name);
            this.getCard();
            System.out.println(this.getPlayerHand());
            }
        }
        return result;
    }

    String getPlayerHand(){
        return String.format("Игрок [%s]: рука: %s; сумма очков: %d;", this.name, this.hand, this.getPoints());
    }
    public String toString() {
        return String.format("Игрок [%s]: победы = %d; проигрыши = %d;", this.name, this.win, this.lose);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
