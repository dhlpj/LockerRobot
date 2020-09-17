package com.thoughtworks.locker;

import com.thoughtworks.locker.bag.SmallBag;
import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.IncorrectTicketTypeException;
import com.thoughtworks.locker.exception.TicketInvalidException;
import com.thoughtworks.locker.ticket.SmallTicket;
import com.thoughtworks.locker.ticket.Ticket;

import java.util.HashMap;
import java.util.Map;

public class SmallLocker {
    private final int capacity;
    private Map<Ticket, SmallBag> ticketBagMap = new HashMap<>();
    public SmallLocker(int capacity) {
        this.capacity = capacity;
    }

    public SmallTicket save(SmallBag smallBag) {
        if (isFull()) {
            throw new FullCapacityException();
        }

        SmallTicket smallTicket = new SmallTicket();
        ticketBagMap.put(smallTicket, smallBag);
        return smallTicket;
    }

    public SmallBag take(Ticket ticket) {
        if (!isSmallTicket(ticket)) {
            throw new IncorrectTicketTypeException();
        }
        if (!isContain(ticket)) {
            throw new TicketInvalidException();
        }

        return ticketBagMap.remove(ticket);
    }

    private boolean isSmallTicket(Ticket ticket) {
        return ticket instanceof SmallTicket;
    }

    private boolean isFull() {
        return ticketBagMap.size() >= capacity;
    }

    private boolean isContain(Ticket ticket) {
        return ticketBagMap.containsKey(ticket);
    }
}
