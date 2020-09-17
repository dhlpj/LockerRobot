package com.thoughtworks.locker;

import com.thoughtworks.locker.bag.MediumBag;
import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.ticket.MediumTicket;

import java.util.HashMap;

public class MediumLocker {
    private final int capacity;
    private HashMap<MediumTicket, MediumBag> ticketBagMap = new HashMap<>();

    public MediumLocker(int capacity) {
        this.capacity = capacity;
    }

    public MediumTicket save(MediumBag mediumBag) {
        if (ticketBagMap.size() >= capacity) {
            throw new FullCapacityException();
        }

        MediumTicket mediumTicket = new MediumTicket();
        ticketBagMap.put(mediumTicket, mediumBag);
        return mediumTicket;
    }
}
