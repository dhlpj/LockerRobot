package com.thoughtworks.locker.robot;

import com.thoughtworks.locker.Bag;
import com.thoughtworks.locker.Locker;
import com.thoughtworks.locker.Ticket;
import com.thoughtworks.locker.exception.IncorrectTicketTypeException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.List;

public abstract class LockerRobot {
    protected List<Locker> lockers;

    public LockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }


    public Ticket save(Bag bag) {
        return getAvailableLocker().save(bag);
    }

    public Bag take(Ticket ticket) {
        if (!isTypeMatched(ticket)) {
            throw new IncorrectTicketTypeException();
        }

        return lockers.stream()
                .filter(locker -> locker.isContain(ticket))
                .findFirst()
                .map(locker -> locker.take(ticket))
                .orElseThrow(TicketInvalidException::new);
    }

    protected abstract Locker getAvailableLocker();

    protected abstract boolean isTypeMatched(Ticket ticket);
}
