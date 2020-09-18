package com.thoughtworks.locker.robot;

import com.thoughtworks.locker.Locker;
import com.thoughtworks.locker.enums.Type;
import com.thoughtworks.locker.exception.FullCapacityException;

import java.util.List;

public class PrimaryLockerRobot extends LockerRobot {

    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    protected Locker getAvailableLocker() {
        return lockers.stream()
                .filter(locker -> !locker.isFull())
                .findFirst()
                .orElseThrow(FullCapacityException::new);
    }

    protected boolean isTypeMatched(Type type) {
        return type == Type.M;
    }
}
