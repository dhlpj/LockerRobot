package com.thoughtworks.locker;

import java.util.Comparator;
import java.util.List;

public class SuperLockerRobot {
    private final List<Locker> largeLockers;

    public SuperLockerRobot(List<Locker> largeLockers) {
        this.largeLockers = largeLockers;
    }

    public Ticket save(Bag largeBag) {
        return largeLockers.stream()
                .filter(locker -> !locker.isFull())
                .max(Comparator.comparing(Locker::idleRate))
                .map(locker -> locker.save(largeBag))
                .orElse(null);
    }
}
