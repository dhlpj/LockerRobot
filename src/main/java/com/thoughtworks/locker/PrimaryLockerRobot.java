package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.Type;
import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.IncorrectTicketTypeException;
import com.thoughtworks.locker.exception.TicketInvalidException;

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
        if (!isTypeMatched(mediumTicket.getType())) {
            throw new IncorrectTicketTypeException();
        }

        return mediumLockers.stream()
                .filter(locker -> locker.isContain(mediumTicket))
                .findFirst()
                .map(locker -> locker.take(mediumTicket))
                .orElseThrow(TicketInvalidException::new);
    }

    private boolean isTypeMatched(Type ticketType) {
        return ticketType == Type.M;
    }
}
