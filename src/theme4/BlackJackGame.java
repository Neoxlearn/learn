package theme4;

import java.util.Scanner;

public class BlackJackGame {
    private final static int MIN_CARDS_DESK = 4;
    private final static String COMPUTER_NAME = "КОМПЬЮТЕР";
    private final static int MAX_SCORE = 21;
    private final static int SCORE_FOR_MOVE = 15;

    private final Desk desk;

    protected BlackJackGame(Desk desk){
        this.desk = desk;
    }

    private void startGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя: ");
        String playerName = scanner.nextLine();

        this.desk.shuffleDesk();
        Player player = new Player(playerName, MAX_SCORE, this.desk);
        Player computer = new Player(COMPUTER_NAME, MAX_SCORE, SCORE_FOR_MOVE, this.desk, true);
        boolean gameNext = true;
        int count = 1;
        while (gameNext && this.desk.getAmountCards() >= MIN_CARDS_DESK){
            System.out.println("Тусуем колоду");
            this.desk.shuffleDesk();
            System.out.println("Начинаем игру N " + count);
            if (count > 1){
                player.setNewHand();
                computer.setNewHand();
            }
            count ++;
            gameNext = game(player, computer);
            System.out.println();
            System.out.println(player);
            System.out.println(computer);
            System.out.println();
        }
        printFullWinner(player,computer);
        System.out.println("Конец игры.");
        scanner.close();

    }

    private boolean game(Player player1, Player player2){

        System.out.println(player1.getPlayerHand());
        System.out.println(player2.getPlayerHand());
        boolean nextMove1 = true;
        boolean nextMove2 = true;
        while (nextMove1 || nextMove2){
            nextMove1 = player1.move();
            nextMove2 = player2.move();
        }

        return whoIsWinner(player1, player2);
    }

    private boolean whoIsWinner(Player player1, Player player2){
        boolean result = true;

        if(result = !player1.isLose() && !player2.isLose()){
            if (player1.getPoints() > player2.getPoints()){
                player1.incWin();
                player2.incLose();
                printWinner(player1, player2);
            }
            else if (player1.getPoints() < player2.getPoints()){
                player1.incLose();
                player2.incWin();
                printWinner(player2,player1);
            } else {
                if (player1.getPoints() == MAX_SCORE){
                    player1.incWin();
                    player2.incLose();
                    printWinner(player1, player2);
                } else if (player2.getPoints() == MAX_SCORE){
                    player1.incLose();
                    player2.incWin();
                    printWinner(player2, player1);
                }else
                     System.out.println("Победителя нет");

            }
            player1.upadate();
            player2.upadate();

        }
        if (player1.isLose() && !player2.isLose()){
            player1.incLose();
            player2.incWin();
            printWinner(player2, player1);
        }else if (!player1.isLose() && player2.isLose()){
            player1.incWin();
            player2.incLose();
            printWinner(player1, player2);
        } else if (player1.isLose() && player2.isLose()){
            System.out.println("Победителя нет");
        }

        return result;
    }

    public void printWinner(Player winner, Player looser){
        winner.getPlayerHand();
        looser.getPlayerHand();
        System.out.println("Победитель этой игры: " + winner.getName());
    }

    private void printFullWinner(Player first, Player second) {
        Player winner = null;
        if (first.isLose() && !second.isLose()) {
            winner = second;
            System.out.printf("Победитель [%s]! Побед: %d \n", winner.getName(), winner.getWin());
        } else if (second.isLose() && !first.isLose()) {
            winner = first;
        } else {
            System.out.println(first);
            System.out.println(second);
            if (first.getWin() > second.getWin()) {
                winner = first;
            } else if (first.getWin() < second.getWin()) {
                winner = second;
            }
        }
        System.out.println(winner != null ?
                String.format("Победитель [%s]! Побед: %d", winner.getName(), winner.getWin()) :
                "Победителя нет!");
    }

    public static void main(String[] args) {
        new BlackJackGame(new Desk()).startGame();

    }
}
