package Code;

import java.util.Scanner;

public class Haggle {
    private static player[] players;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] print = new int[num];

        players = new player[num];
        for (int i = 0; i < players.length; i++) {
            print[i] = i;
            players[i] = new player();
        }
        for (int i = 0; i < num; i++) {
            players[i].name = sc.next();
            players[i].card = sc.next();
        }
        for (int i = 0; i < num; i++)
            count(players[i]);

        //8.
        int yellow = 0;
        int temp = -1;
        for (int i = 0; i < num; i++) {
            if (players[i].color[0] > players[yellow].color[0])
                yellow = i;
            else if (players[i].color[0] == players[yellow].color[0])
                temp = players[i].color[0];
        }
        if (players[yellow].color[0] == temp) {
            yellow = 0;
            for (int i = 0; i < num - 1; i++)
                if (players[i].color[0] > players[yellow].color[0] || players[i].color[0] < temp)
                    yellow = i;
        }
        players[yellow].score += Math.pow(players[yellow].color[0], 2);

        //12.
        int red = 0;
        temp = -1;
        for (int i = 0; i < num; i++) {
            if (players[i].color[2] > players[red].color[2])
                red = i;
            else if (players[i].color[2] == players[red].color[2])
                temp = players[i].color[2];
        }
        if (players[red].color[2] != temp)
            players[red].score += players[red].color[2] * 3;

        for (int i = 0; i < num; i++) {
            if (players[i].blue > 0) //6.
                players[i].score -= players[i].blue * 10;

            if (tower(players[i])) //11.
                players[i].score *= 2;
        }

        for (int i = players.length - 1; i >= 0; i--)
            for (int j = 0; j < i; j++)
                if (players[print[j]].score < players[print[j + 1]].score) {
                    int tmp = print[j];
                    print[j] = print[j + 1];
                    print[j + 1] = tmp;
                }

        for (int e : print) {
            System.out.println(out(players[e]));
        }

        System.out.println("恭喜 " + players[print[0]].name + " 獲得勝利!");
    }

    public static String out(player person) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(person.name);
        buffer.append(" -");

        for (int j = 0; j < 5; j++) {
            if (person.color[j] > 0) {
                switch (j) {
                    case 0:
                        buffer.append(" 黃 ");
                        break;
                    case 1:
                        buffer.append(" 藍 ");
                        break;
                    case 2:
                        buffer.append(" 紅 ");
                        break;
                    case 3:
                        buffer.append(" 橘 ");
                        break;
                    case 4:
                        buffer.append(" 白 ");
                        break;
                }
                buffer.append(person.color[j]);
            }
        }

        buffer.append(" / 總分 ");
        buffer.append(person.score);
        return buffer.toString();
    }

    public static void count(player person) {
        String s = person.card;
        for (int i = 0; i < s.length(); i++) {
            String temp = s.substring(i, i + 1);
            switch (temp) {
                case "Y":
                    person.color[0] += 1;
                    break;
                case "B":
                    person.color[1] += 1;
                    break;
                case "R":
                    person.color[2] += 1;
                    break;
                case "O":
                    person.color[3] += 1;
                    break;
                case "W":
                    person.color[4] += 1;
                    break;
            }
        }
        person.score = score(person);
    }

    public static int score(player person) {
        int score = 0;
        int[] count = person.color;

        int orange = 0;
        int white = 0;

        if (count[0] >= 2) { //13.
            white = count[0] / 2;
        }

        if (count[1] >= 3) {//14.
            orange = count[1] / 3;
        }

        for (int j : count) { //9.
            if (j >= 7)
                return 0;
        }

        //Yellow
        score += count[0]; //3.

        //Blue
        score += count[1] * 2; //3.

        //Red
        score += count[2] * 3;

        //Orange
        if (count[3] > count[1])    //5.
            score += count[1] * 4;
        else
            score += count[3] * 4;//1.

        if (orange > count[3]) //14.
            score += count[4] * 12;
        else
            score += orange * 12;

        //White
        if (count[4] <= 3)
            score += count[4] * 5; //4

        if (white > count[4]) //13.
            score += count[4] * 5;
        else
            score += white * 5;

        //all
        if (count[1] >= 5)
            blue(person); //6.
        if (count[2] >= 3)
            for (int i = 0; i < count[2]; i += 3)
                unblue(person); //7.

        int times = 5;
        for (int e : count) {
            if (e < times)
                times = e;
        }
        if (times > 0)
            score += times * 10;


        int num = 5; //10.
        for (int j : count) {
            if (j < num)
                num = j;
        }

        return score;
    }

    public static void blue(player person) {
        for (Haggle.player player : players)
            if (!player.equals(person))
                player.blue += 1;
    }

    public static void unblue(player person) {
        for (Haggle.player player : players)
            if (player.equals(person))
                player.blue -= 1;
    }

    public static boolean tower(player person) { //11.
        int[] count = person.color;
        boolean has = true;
        int i = 4;
        while (has) {
            has = false;
            if (i == 0)
                return true;
            else
                for (int times : count) {
                    if (times == i) {
                        has = true;
                        break;
                    }
                }
            i--;
        }
        return false;
    }

    static class player {
        String name;
        String card;
        int[] color = new int[5];
        int score;
        int blue;
    }
}
