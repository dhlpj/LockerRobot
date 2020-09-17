package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.Type;
import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.IncorrectTicketTypeException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private final int capacity;
    private final Type type;
    private final Map<Ticket, Bag> ticketBagMap = new HashMap<>();

    public Locker(Type type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public Ticket save(Bag bag) {
        if (isFull()) {
            throw new FullCapacityException();
        }

        Ticket ticket = new Ticket(type);
        ticketBagMap.put(ticket, bag);
        return ticket;
    }

    public Bag take(Ticket ticket) {
        if (!isTypeMatched(ticket.getType())) {
            throw new IncorrectTicketTypeException();
        }
        if (!isContain(ticket)) {
            throw new TicketInvalidException();
        }

        return ticketBagMap.remove(ticket);
    }

    private boolean isTypeMatched(Type ticketType) {
        return ticketType == type;
    }

    public boolean isFull() {
        return ticketBagMap.size() >= capacity;
    }

    public boolean isContain(Ticket ticket) {
        return ticketBagMap.containsKey(ticket);
    }
}
