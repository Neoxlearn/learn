/*
Габидуллин А.Э.
Вариант №15.
Задача 3.15.
3.15 Во входном файле находятся результаты чемпионата России по футболу в виде списка матчей. Каждый матч обозначен в
отдельной строке: название первой команды, название второй команды, счёт матча в виде двух целых чисел. Каждая команда
сыгралас каждой ровно один раз. Построить и вывести в выходной файл итоговую таблицу чемпионата: в строках - команды; в
столбцах итоги – количество побед, ничьих, поражений, забитых и пропущенных мячей, количество очков, занятое место. За
каждую победу команде начисляется 3 очка, за ничью - 1 очко, за поражение 0 очков. Таблица должна быть упорядочена по
убыванию набранных очков. Если несколько команд набрали одинаковое число очков, то учитываются следующие критерии: лучшая
разность забитых и пропущенных мячей, количество побед, личная встреча.
 */

package theme3;

import java.io.*;
import java.util.*;

public class Team {
    private String name;
    private int wins;
    private int losings;
    private int draw;
    private int goals;
    private int gc;
    private int points;
    private int goalsgc;
    private ArrayList<String> loosers;

    public Team(String name) {
        this.name = name;
        loosers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getGc() {
        return gc;
    }

    public int getWins() {
        return wins;
    }

    public int getGoalsgc() {


        return (this.goals - this.gc);
    }

    public static void main(String[] args) throws IOException {
        String filename = "bigtest.txt";
        ArrayList<String> teamsName =  createArray(filename);
        ArrayList<Team> teams = createObjects(teamsName);
        createFields(teams, filename);
        sortForTable(teams);
        makeTabble(teams);
        //Тестовая таблица для вывода в консоль
        consoleTable(teams);
    }

    //Тестовая таблица для вывода в консоль
    private static void consoleTable(ArrayList<Team> teams){
        System.out.printf("%-15s%-10s%-9s%-13s%-11s%-15s%-10s%-10s%n","Команда","Победы", "Ничьи", "Поражения", "Забитые", "Пропущенные", "Очки", "Место");
        System.out.println("----------------------------------------------------------------------------------------");
        int count = 1;
        for (Team team: teams
        ) {
            System.out.printf("%-17s%-10d%-11d%-12d%-13d%-11d%-11d%-10d%n", team.name, team.wins, team.draw, team.losings, team.goals, team.gc, team.points, count);
            count++;
        }
    }

// Считываем файл и составляем список команд
    private static ArrayList<String> createArray(String filename) throws IOException {
        ArrayList<String> nameslist = new ArrayList<>();
        FileReader fl = new FileReader(filename);
        BufferedReader reader = new BufferedReader(fl);
        String line = null;
        while ((line = reader.readLine()) != null){
            String[] names = line.substring(0, line.indexOf(",", line.indexOf(",") + 1)).split(", ");

            for (String str:names
            ) {
                //String name = str;
                if (!nameslist.contains(str)) {
                    nameslist.add(str);

                }
            }
        }
        fl.close();
        reader.close();
        return nameslist;
    }

// Из списка команд формируем ArrayList команд
    private static ArrayList<Team> createObjects(ArrayList<String> teamsName){
        ArrayList<Team> teams = new ArrayList<>();
        for (String team: teamsName
             ) {
                teams.add(new Team(team));
        }
        return teams;
    }
// Заполняем поля класса для каждой команды
    private static void createFields(ArrayList<Team> teams, String filename) throws IOException {
        FileReader fl = new FileReader(filename);
        BufferedReader reader = new BufferedReader(fl);
        String line = null;
        while ((line = reader.readLine()) != null){
            String[] fields = line.split(", ");
            int number1 = Integer.parseInt(fields[2]);
            int number2 = Integer.parseInt(fields[3]);

            for (Team team: teams
                 ) {
                if (fields[0].equals(team.name)){
                    if(number1 > number2){
                        winProcess(team, fields[1], number1, number2);
                    }
                    else if (number1 < number2){
                        loseProcess(team, number1, number2);
                    }
                    else drawProcess(team, number1, number2);
                }
                if (fields[1].equals(team.name)){
                    if (number1 > number2){
                        loseProcess(team, number2, number1);
                    }
                    else if (number1 < number2){
                        winProcess(team, fields[0], number2, number1);

                    }
                    else drawProcess(team, number1, number2);
                }
            }
        }
        fl.close();
        reader.close();
    }
 //   Логика заполнения поле при разных исходах матча
    private static void winProcess(Team team, String looseTeam ,int goal, int goalc){
        team.wins = team.wins + 1;
        team.goals = team.goals + goal;
        team.gc = team.gc + goalc;
        team.points = team.points + 3;
        team.loosers.add(looseTeam);
    }

    private static void loseProcess(Team team, int goal, int goalc){
        team.losings = team.losings + 1;
        team.goals = team.goals + goal;
        team.gc = team.gc + goalc;

    }

    private static void drawProcess(Team team, int goal, int goalc){
        team.draw = team.draw + 1;
        team.goals = team.goals + goal;
        team.gc = team.gc + goalc;
        team.points = team.points + 1;

    }
    // Сортировка таблицы
    private static void sortForTable(ArrayList<Team> teams) {
        Collections.sort(teams, Comparator.comparing(Team::getPoints).thenComparing(Team::getGoalsgc)
                .thenComparing(Team::getWins).reversed());
        for (int i = 1; i < teams.size(); i++) {
            if ((teams.get(i).getPoints() == teams.get(i - 1).getPoints()) && (teams.get(i).getGoalsgc() == teams.get(i - 1).getGoalsgc())
                    && (teams.get(i).getWins() == teams.get(i - 1).getWins()) && teams.get(i).loosers.contains(teams.get(i - 1).name)){                     //isWin(filename, teams.get(i).getName(), teams.get(i - 1).getName())) {
                swap(teams, i, i - 1);

                for (int z = i - 1; (z - 1) >= 0; z--) {
                    if ((teams.get(z).getPoints() == teams.get(z - 1).getPoints()) && (teams.get(z).getGoalsgc() == teams.get(z - 1).getGoalsgc())
                            && (teams.get(z).getWins() == teams.get(z - 1).getWins()) && teams.get(z).loosers.contains(teams.get(z - 1).name)){             //isWin(filename, teams.get(z).getName(), teams.get(z - 1).getName())) {
                        swap(teams, z, z - 1);

                    } else {
                        break;
                    }
                }
            }
        }

    }

    private static void swap(ArrayList<Team> teams, int a, int b){
        Team buff = teams.get(a);
        teams.set(a, teams.get(b));
        teams.set(b, buff);

    }
    // Проверяем, победила ли команда teamName1 TODO: Не используем, заменил на Arraylist loosers и проверку в createFields.
    private static boolean isWin(String filename, String teamName1, String teamName2) throws IOException {
        boolean win = false;
        FileReader fl = new FileReader(filename);
        BufferedReader reader = new BufferedReader(fl);
        String line = null;
        while ((line = reader.readLine()) != null){
            String[] fields = line.split(", ");
            int number1 = Integer.parseInt(fields[2]);
            int number2 = Integer.parseInt(fields[3]);
            if (teamName1.equals(fields[0]) && teamName2.equals(fields[1])){
                if (number1 >= number2){
                    win = true;
                    break;
                } else break;
            }
            else if (teamName1.equals(fields[1]) && teamName2.equals(fields[0])){
                if (number1 <= number2){
                    win = true;
                    break;
                }else break;
            }
        }
        fl.close();
        reader.close();
        return win;
    }
// Создаем таблицу в файле
    private static void makeTabble(ArrayList<Team> teams) throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File("bigouttest.txt"), false)));
        pw.printf("%-15s%-10s%-9s%-13s%-11s%-15s%-10s%-10s%n","Команда","Победы", "Ничьи", "Поражения", "Забитые", "Пропущенные", "Очки", "Место");
        pw.println("----------------------------------------------------------------------------------------");
        int rank = 1;
        for (Team team: teams
             ) {
            pw.printf("%-17s%-10d%-11d%-12d%-13d%-11d%-11d%-10d%n", team.name, team.wins, team.draw, team.losings, team.goals, team.gc, team.points, rank);
            rank ++;
        }
        pw.close();
    }
}
