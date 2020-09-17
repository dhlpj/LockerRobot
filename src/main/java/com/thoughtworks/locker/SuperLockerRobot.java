package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.Type;

import java.util.List;

public class SuperLockerRobot {
    private final List<Locker> lockers;

    public SuperLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag largeBag) {
        return new Ticket(Type.L);
    }
}
