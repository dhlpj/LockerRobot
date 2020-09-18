package com.thoughtworks.locker.robot;

import com.thoughtworks.locker.Locker;
import com.thoughtworks.locker.Ticket;
import com.thoughtworks.locker.enums.Type;

import java.util.Comparator;
import java.util.List;

public class SuperLockerRobot extends LockerRobot{

    public SuperLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    protected Locker getAvailableLocker() {
        return lockers.stream()
                .filter(locker -> !locker.isFull())
                .max(Comparator.comparing(Locker::idleRate))
                .orElse(null);
    }

    @Override
    protected boolean isTypeMatched(Ticket ticket) {
        return ticket.getType() == Type.L;
    }
}
