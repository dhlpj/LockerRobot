package com.thoughtworks.locker.robot;

import com.thoughtworks.locker.Bag;
import com.thoughtworks.locker.Locker;
import com.thoughtworks.locker.Ticket;
import com.thoughtworks.locker.enums.Type;
import com.thoughtworks.locker.exception.ConfigurationErrorException;
import com.thoughtworks.locker.exception.IncorrectTicketTypeException;
import com.thoughtworks.locker.exception.TicketInvalidException;

import java.util.List;

public abstract class LockerRobot {
    protected List<Locker> lockers;

    public LockerRobot(List<Locker> lockers) {
        validateLockerType(lockers);

        this.lockers = lockers;
    }

    private void validateLockerType(List<Locker> lockers) {
        boolean isLockerTypeNotMatched = lockers.stream()
                .anyMatch(locker -> !isTypeMatched(locker.getType()));
        if (isLockerTypeNotMatched) {
            throw new ConfigurationErrorException();
        }
    }

    public Ticket save(Bag bag) {
        return getAvailableLocker().save(bag);
    }

    public Bag take(Ticket ticket) {
        if (!isTypeMatched(ticket.getType())) {
            throw new IncorrectTicketTypeException();
        }

        return lockers.stream()
                .filter(locker -> locker.isContain(ticket))
                .findFirst()
                .map(locker -> locker.take(ticket))
                .orElseThrow(TicketInvalidException::new);
    }

    protected abstract Locker getAvailableLocker();

    protected abstract boolean isTypeMatched(Type type);
}
