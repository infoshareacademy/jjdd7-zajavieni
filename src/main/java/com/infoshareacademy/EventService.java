package com.infoshareacademy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventService {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001b[33;1m";

    public void printListOfEvents(List<Event> eventList) throws IOException {
        int counter = 1;
        for (Event event : eventList) {
            System.out.println(ANSI_YELLOW + counter + "." + "\tNazwa wydarzenia: " + ANSI_RESET + event.getName() +
                    ANSI_YELLOW + "\n\tMiejsce: " + ANSI_RESET + event.getPlace().getName() + ANSI_YELLOW + "\n\tData startu: "
                    + ANSI_RESET + event.getStartDate() + ANSI_YELLOW + "\n\tNazwa Organizatora: " + ANSI_RESET + event.getOrganizer().getDesignation() + "\n");
            counter++;
        }
    }
}