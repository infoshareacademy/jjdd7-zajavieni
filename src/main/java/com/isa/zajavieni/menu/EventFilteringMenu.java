package com.isa.zajavieni.menu;

import com.isa.zajavieni.jsonclasses.Event;
import com.isa.zajavieni.repository.EventList;
import com.isa.zajavieni.service.EventFilter;
import com.isa.zajavieni.service.EventPrinter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class EventFilteringMenu {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001b[33;1m";

    public void filter() throws IOException {
        EventFilter eventFilter = new EventFilter();
        EventPrinter eventPrinter = new EventPrinter();
        eventPrinter.printListOfEvents(eventFilter.filterEventsList(EventList.getEventList(), enterStartDate(),
                enterEndDate(), enterNameOfOrganizer()));
        chooseEndingOption();
    }

    private void chooseEndingOption() throws IOException {
        System.out.println("Co chcesz teraz zrobić? ");
        System.out.println("1. Kontynuuj filtrowanie.");
        System.out.println("2. Wróć do wyrzukiwania.");
        System.out.println("3. Wróć do głównego menu.");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                filter();
                break;
            case "2":
                new EventSearchingMenu().printSearchMenu();
                break;
            case "3":
                new MainMenu().mainMenu();
                break;
            default:
                System.out.println("Wpisałeś coś niewłaściwego, wybierz liczbę z zakresu menu.");
                chooseEndingOption();
        }
    }
    private Date enterStartDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz datę początkową filtrowania (RRRR-MM-DD): ");
        Date startDate = null;
        Pattern datePattern = compile("[1-2][0,1,9][0-9][0-9]\\-[0-1][0-9]\\-[0-3][0-9]");
        do {
            String startDateString = scanner.nextLine();
            Matcher matcher = datePattern.matcher(startDateString);
            if (matcher.matches()) {
                startDate = formatDate(startDateString);
            } else {
                System.out.println("Wpisano zły format daty. Wpisz ponownie datę w formacie: RRRR-MM-DD");
            }
        } while (startDate == null);
        return startDate;
    }

    private Date enterEndDate() throws IOException {
        EventList eventList = new EventList();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wpisz datę końcową filtrowania (RRRR-MM-DD): ");
        Date endDate = null;
        Pattern datePattern = compile("[1-2][0,1,9][0-9][0-9]\\-[0-1][0-9]\\-[0-3][0-9]");
        do {
            String endDateString = scanner.nextLine();
            Matcher matcher = datePattern.matcher(endDateString);
            if (matcher.matches()) {
                endDate = formatDate(endDateString);
            } else {
                System.out.println("Wpisano zły format daty. Wpisz ponownie datę w formacie: RRRR-MM-DD");
            }
        } while (endDate == null);
        System.out.println(ANSI_YELLOW + "Nazwy organizatorów:" + ANSI_RESET);
        printOrganizerList(eventList.getEventList());
        return endDate;
    }

    private void printOrganizerList(List<Event> eventList) throws IOException {
        List<String> organizersList = new ArrayList<>();
        for (Event event : eventList) {
            if (!organizersList.contains(event.getOrganizer().getDesignation())) {
                organizersList.add(event.getOrganizer().getDesignation());
            }
        }
        int counter = 1;
        for (String organizer : organizersList) {
            System.out.println(ANSI_YELLOW + counter + "." + " " + ANSI_RESET + organizer);
            counter++;
        }
    }

    private List<String> enterNameOfOrganizer() throws IOException {
        Scanner scanner = new Scanner(System.in);
        EventList eventList = new EventList();
        List<String> organizersList = new ArrayList<>();
        boolean isSearchFinished = false;
        do {
            System.out.println("Podaj nazwę organizatora z listy:");
            String organizer = scanner.nextLine();
            boolean isParticularOrganizerFound = false;
            for (int i = 0; i < EventList.getEventList().size(); i++) {
                if (organizer.toLowerCase().replaceAll("\\s", "").equals(eventList.getEventList()
                        .get(i).getOrganizer().getDesignation().toLowerCase().replaceAll("\\s", ""))) {
                    organizersList.add(organizer);
                    isParticularOrganizerFound = true;
                    break;
                }
            }
            if (isParticularOrganizerFound == false) {
                System.out.println("Nie ma organizatora " + organizer + " na liście wydarzeń.");
            }
            System.out.println("Czy chcesz dodac kolejengo organizatora? (t/n)");
            String choice = scanner.nextLine();
            switch (choice) {
                case "t":
                    break;
                case "n":
                    isSearchFinished = true;
                    break;
                default:
                    System.out.println("Wpisałeś coś niewłaściwego, wybierz t lub n");
                    scanner.nextLine();
                    continue;
            }
        } while (isSearchFinished == false);
        return organizersList;
    }

    private Date formatDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date formatDate = null;
        try {
            formatDate = formatter.parse(date);
        } catch (ParseException e) {
            System.out.println("Wystąpił błąd formatowania!");
        }
        return formatDate;
    }
}