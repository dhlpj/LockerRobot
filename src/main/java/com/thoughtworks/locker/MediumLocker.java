package com.thoughtworks.locker;

import com.thoughtworks.locker.bag.MediumBag;
import com.thoughtworks.locker.ticket.MediumTicket;

public class MediumLocker {
    private final int capacity;

    public MediumLocker(int capacity) {
        this.capacity = capacity;
    }

    public MediumTicket save(MediumBag mediumBag) {
        return new MediumTicket();
    }
}
