package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AviaSoulsTest {

    @Test
    public void ticketCompareToTest() {
        Ticket ticket1 = new Ticket("Москва", "Казань", 20_000, 12_00, 14_00);
        Ticket ticket2 = new Ticket("Казань", "Саратов", 15_000, 15_00, 16_00);
        Ticket ticket3 = new Ticket("Саратов", "Москва", 25_000, 11_00, 13_00);

        assertTrue(ticket1.compareTo(ticket2) > 0);
        assertTrue(ticket2.compareTo(ticket3) < 0);
        assertTrue(ticket1.compareTo(ticket3) < 0);
    }

    @Test
    public void ticketSearchTest() {
        Ticket ticket1 = new Ticket("Москва", "Казань", 20_000, 12_00, 14_00);
        Ticket ticket2 = new Ticket("Казань", "Саратов", 15_000, 15_00, 16_00);
        Ticket ticket3 = new Ticket("Саратов", "Москва", 25_000, 11_00, 13_00);

        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] searchResult = manager.search("Казань", "Саратов");

        Assertions.assertArrayEquals(new Ticket[]{ticket2}, searchResult);

    }

    @Test
    public void ticketTimeComparatorTest() {
        Ticket ticket1 = new Ticket("Москва", "Казань", 20_000, 12_00, 14_00);
        Ticket ticket2 = new Ticket("Казань", "Саратов", 15_000, 15_00, 16_00);
        Ticket ticket3 = new Ticket("Саратов", "Москва", 25_000, 11_00, 14_00);

        Ticket[] tickets = {ticket1, ticket2, ticket3};

        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Arrays.sort(tickets, timeComparator);

        Assertions.assertArrayEquals(new Ticket[]{ticket2, ticket1, ticket3}, tickets);
    }

    @Test
    public void logicComparatorTest() {
        Ticket ticket1 = new Ticket("Москва", "Казань", 20_000, 12_00, 14_00);
        Ticket ticket2 = new Ticket("Москва", "Казань", 15_000, 15_00, 16_00);
        Ticket ticket3 = new Ticket("Москва", "Казань", 25_000, 11_00, 19_00);

        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Comparator<Ticket> ticketComparator = new TicketTimeComparator();
        Ticket[] searchResult = manager.searchAndSortBy("Москва", "Казань", ticketComparator);

        Assertions.assertArrayEquals(new Ticket[]{ticket2, ticket1, ticket3}, searchResult);
    }
}
