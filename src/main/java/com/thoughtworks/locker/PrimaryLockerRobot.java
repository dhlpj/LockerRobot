package com.thoughtworks.locker;

import java.util.List;

public class PrimaryLockerRobot {
    private List<Locker> mediumLockers;

    public PrimaryLockerRobot(List<Locker> mediumLockers) {
        this.mediumLockers = mediumLockers;
    }


    public Ticket save(Bag mediumBag) {
        return mediumLockers.stream()
                .filter(locker -> !locker.isFull())
                .findFirst()
                .map(locker -> locker.save(mediumBag))
                .orElse(null);
    }
}