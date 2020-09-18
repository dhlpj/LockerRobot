package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.Type;
import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.robot.PrimaryLockerRobot;
import com.thoughtworks.locker.robot.SuperLockerRobot;
import org.junit.Test;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LockerRobotManagerTest {
    @Test
    public void should_bag_in_small_locker_and_return_small_ticket_when_save_small_bag_given_manager_manage_1_small_locker_and_1_primary_locker_robot_and_1_super_locker_robot_and_all_with_free_capacity() {
        Locker smallLocker = new Locker(Type.S, 10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(new Locker(Type.M, 10)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(new Locker(Type.L, 10)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(singletonList(smallLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));
        Bag smallBag = new Bag(Type.S);

        Ticket smallTicket = lockerRobotManager.save(smallBag);

        assertEquals(smallBag, smallLocker.take(smallTicket));
        assertNotNull(smallTicket);
        assertEquals(Type.S, smallTicket.getType());
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_save_small_bag_given_manager_manage_1_small_locker_is_full_and_robots_are_not_full() {
        Locker smallLocker = new Locker(Type.S, 1);
        smallLocker.save(new Bag(Type.S));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(new Locker(Type.M, 10)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(new Locker(Type.L, 10)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(singletonList(smallLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));
        Bag smallBag = new Bag(Type.S);

        lockerRobotManager.save(smallBag);
    }

    @Test
    public void should_save_bag_in_primary_locker_robot_and_return_medium_ticket_when_save_medium_bag_given_manager_manage_1_small_locker_and_1_primary_locker_robot_and_1_super_locker_robot_and_all_with_free_capacity() {
        Locker smallLocker = new Locker(Type.S, 10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(new Locker(Type.M, 10)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(new Locker(Type.L, 10)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(singletonList(smallLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));
        Bag mediumBag = new Bag(Type.M);

        Ticket mediumTicket = lockerRobotManager.save(mediumBag);

        assertEquals(mediumBag, primaryLockerRobot.take(mediumTicket));
        assertNotNull(mediumTicket);
        assertEquals(Type.M, mediumTicket.getType());
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_save_medium_bag_given_manager_manage_1_small_locker_and_1_super_locker_robot_are_not_full_but_primary_locker_robot_is_full() {
        Locker smallLocker = new Locker(Type.S, 10);
        Locker mediumLocker = new Locker(Type.M, 1);
        mediumLocker.save(new Bag(Type.M));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(mediumLocker));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(new Locker(Type.L, 10)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(singletonList(smallLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));
        Bag mediumBag = new Bag(Type.M);

        lockerRobotManager.save(mediumBag);
    }
}
