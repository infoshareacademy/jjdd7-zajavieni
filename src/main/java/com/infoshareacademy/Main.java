package com.infoshareacademy;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("1. Lista wszystkich: ");
        System.out.println("2. Wyszukaj wydarzenie: ");
        System.out.println("3. Twoje ulubione wydarzenia: ");
        System.out.println("Prosze podac cyfre z zakresu menu");

        Menu menu = new Menu();
        ScannerForClasses scannerForClasses = new ScannerForClasses();
        //Scanner scanner = new Scanner(System.in);

        //short menuLevel;
        while (true) {
            //String menuChoice = scanner.nextLine();
            String menuChoice = ScannerForClasses.scan.next();
            ScannerForClasses.scan.close();
            if (menuChoice.equals("exit")) {
                break;
            } else if (!menuChoice.equals("exit")) {
                int menuChoiceParsed = parseStringToInt(menuChoice);
                menu.menu(menuChoiceParsed);
            }
        }
    }

    private static int parseStringToInt(String menuChoice) {
        int menuChoiceParsed = Integer.parseInt(menuChoice);
        return menuChoiceParsed;
    }
}

