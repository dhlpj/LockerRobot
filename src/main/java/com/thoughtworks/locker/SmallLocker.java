package com.thoughtworks.locker;

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

    private boolean isFull() {
        return ticketBagMap.size() >= capacity;
    }

    public SmallBag take(SmallTicket smallTicket) {
        return ticketBagMap.remove(smallTicket);
    }
}
