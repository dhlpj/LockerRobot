package com.thoughtworks.locker;

public class SmallLocker {
    private final int capacity;
    public SmallLocker(int capacity) {
        this.capacity = capacity;
    }

    public SmallTicket save(SmallBag smallBag) {
        return new SmallTicket();
    }
}
