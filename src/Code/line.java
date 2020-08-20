package Code;

import java.util.Scanner;

public class line {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] number = new int[5];
        for (int i = 0; i < number.length; i++)
            number[i] = sc.nextInt();

        for (int i = number.length - 1; i >= 0; i--)
            for (int j = 0; j < i; j++)
                if (number[j] < number[j + 1]) {
                    int temp = number[j];
                    number[j] = number[j + 1];
                    number[j + 1] = temp;
                }

        for (int e : number)
            System.out.println(e);

    }
}
