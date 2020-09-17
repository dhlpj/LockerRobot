package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.HashMap;
import java.util.Map;

public class SmallLocker {
    private final int capacity;
    private Map<SmallTicket, SmallBag> ticketBagMap = new HashMap<>();
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

    public SmallBag take(SmallTicket smallTicket) {
        if (!isContain(smallTicket)) {
            throw new TicketInvalidException();
        }
        return ticketBagMap.remove(smallTicket);
    }

    private boolean isFull() {
        return ticketBagMap.size() >= capacity;
    }

    private boolean isContain(SmallTicket smallTicket) {
        return ticketBagMap.containsKey(smallTicket);
    }
}
