package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.Type;
import com.thoughtworks.locker.robot.PrimaryLockerRobot;
import com.thoughtworks.locker.robot.SuperLockerRobot;

import java.util.List;

public class LockerRobotManager {
    private List<Locker> smallLockers;
    private List<PrimaryLockerRobot> primaryLockerRobots;
    private List<SuperLockerRobot> superLockerRobots;

    public LockerRobotManager(List<Locker> smallLockers, List<PrimaryLockerRobot> primaryLockerRobots, List<SuperLockerRobot> superLockerRobots) {
        this.smallLockers = smallLockers;
        this.primaryLockerRobots = primaryLockerRobots;
        this.superLockerRobots = superLockerRobots;
    }

    public Ticket save(Bag bag) {
        if (bag.getType() == Type.S) {
            return smallLockers.get(0).save(bag);
        }
        return null;
    }
}
