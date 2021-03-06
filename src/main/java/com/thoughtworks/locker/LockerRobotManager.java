package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.Type;
import com.thoughtworks.locker.exception.ConfigurationErrorException;
import com.thoughtworks.locker.exception.IncorrectBagTypeException;
import com.thoughtworks.locker.exception.IncorrectTicketTypeException;
import com.thoughtworks.locker.robot.PrimaryLockerRobot;
import com.thoughtworks.locker.robot.SuperLockerRobot;

import java.util.List;

public class LockerRobotManager {
    private List<Locker> smallLockers;
    private List<PrimaryLockerRobot> primaryLockerRobots;
    private List<SuperLockerRobot> superLockerRobots;

    public LockerRobotManager(List<Locker> smallLockers, List<PrimaryLockerRobot> primaryLockerRobots, List<SuperLockerRobot> superLockerRobots) {
        validateLockerType(smallLockers);

        this.smallLockers = smallLockers;
        this.primaryLockerRobots = primaryLockerRobots;
        this.superLockerRobots = superLockerRobots;
    }

    private void validateLockerType(List<Locker> smallLockers) {
        boolean isLockerTypeNotMatched = smallLockers.stream()
                .anyMatch(locker -> locker.getType() != Type.S);
        if (isLockerTypeNotMatched) {
            throw new ConfigurationErrorException();
        }
    }

    public Ticket save(Bag bag) {
        switch (bag.getType()) {
            case S:
                return smallLockers.get(0).save(bag);
            case M:
                return primaryLockerRobots.get(0).save(bag);
            case L:
                return superLockerRobots.get(0).save(bag);
            default:
                throw new IncorrectBagTypeException();
        }
    }

    public Bag take(Ticket ticket) {
        switch (ticket.getType()) {
            case S:
                return smallLockers.get(0).take(ticket);
            case M:
                return primaryLockerRobots.get(0).take(ticket);
            case L:
                return superLockerRobots.get(0).take(ticket);
            default:
                throw new IncorrectTicketTypeException();
        }
    }
}
