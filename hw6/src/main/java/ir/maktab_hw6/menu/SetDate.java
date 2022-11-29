package ir.maktab_hw6.menu;

import java.time.LocalDate;
import java.util.Scanner;

public class SetDate {
    static Scanner scanner = new Scanner(System.in);

    private static int setMonth() {
        String input = "0";
        int counter = 0;
        while (!input.matches("^0?[1-9]|[1][0-2]$")) {
            if (counter != 0)
                System.out.println("Moth Must Be Between 1-12");
            System.out.print("Month: ");
            input = scanner.nextLine();
            counter++;
        }
        return Integer.parseInt(input);
    }

    private static int setDay() {
        String input = "0";
        int counter = 0;
        while (!input.matches("^0?[1-9]|[12][0-9]|3[01]$")) {
            if (counter != 0)
                System.out.println("Day Must Be Between 1-31");
            System.out.print("Day: ");
            input = scanner.nextLine();
            counter++;
        }
        return Integer.parseInt(input);
    }


    private static int setYear() {
        String input = "0";
        int counter = 0;
        while (!input.matches("^(19)[0-9][0-9]|(20)[0-1][0-9]|(202)[0-2]$")) {
            if (counter != 0)
                System.out.println("Year Must Be Between 1900-2022");
            System.out.print("Year: ");
            input = scanner.nextLine();
            counter++;
        }
        return Integer.parseInt(input);
    }

    public static LocalDate setDate() {
        int year = setYear();
        int month = setMonth();
        int day = setDay();
        return LocalDate.of(year, month, day);
    }
}