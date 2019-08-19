package com.isa.zajavieni.menu;

import com.isa.zajavieni.service.PrinterEvents;
import com.isa.zajavieni.repository.EventList;
import com.isa.zajavieni.service.SearchEvent;

import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    BreadcrumbHistory bh = new BreadcrumbHistory();
    public String mainMenu() throws IOException {
        EventList eventList = new EventList();
        printTextMainMenu();
        Scanner in = new Scanner(System.in);
        String choice = in.next();
        choiceMenu(choice);
        return choice;
    }

    private void printTextMainMenu() {
        System.out.println(bh.toString());
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1. Lista wszystkich wydarzeń");
        System.out.println("     2. Wyszukaj wydarzenie: ");
        System.out.println("     3. Twoje ulubione wydarzenia: ");
        System.out.println("     0. Koniec");
    }

    private void choiceMenu(String choice) throws IOException {
        Scanner in = new Scanner(System.in);
        switch (choice) {
            case "1":
                new PrinterEvents().printListOfEvents(EventList.getEventList());
                bh.addToHistory("1. Lista wszystkich wydarzeń -> ");
                comebackToChoice(choice);
                break;
            case "2":
                new SearchEvent().printSearchMenu();
                bh.addToHistory("2. Wyszukaj wydarzenie: -> ");
                comebackToChoice(choice);
                break;
            case "3":
                System.out.println("Tu będzie lista Twoich ulubionych wydarzeń.");
                bh.addToHistory("Tu będzie lista Twoich ulubionych wydarzeń -> ");
                comebackToChoice(choice);
                break;
            case "0":
                System.out.println("     ****************************************");
                System.out.println("\n     Koniec programu\n\n");
                break;
            default:
                System.out.println("Wpisałeś coś niewłaściwego, wybierz liczbę z zakresu menu.");
                comebackToChoice(choice);
        }
    }

    private void comebackToChoice(String choice) throws IOException {
        System.out.println("Czy chcesz kontynuować? T / N");
        Scanner scanner = new Scanner(System.in);
        String yesOrNot = scanner.nextLine();
        if (yesOrNot.equalsIgnoreCase("n")) {
            choiceMenu("0");
        } else if (yesOrNot.equalsIgnoreCase("t")) {
            bh.removeLast();
            mainMenu();
        }
    }
}