package com.thoughtworks.locker;

import com.thoughtworks.locker.exception.FullCapacityException;

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
                .orElseThrow(FullCapacityException::new);
    }

    public Bag take(Ticket mediumTicket) {
        return mediumLockers.stream()
                .filter(locker -> locker.isContain(mediumTicket))
                .findFirst()
                .map(locker -> locker.take(mediumTicket))
                .orElse(null);
    }
}
