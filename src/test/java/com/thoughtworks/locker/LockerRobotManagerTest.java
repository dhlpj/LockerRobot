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
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(new Locker(Type.M, 1)));
        primaryLockerRobot.save(new Bag(Type.M));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(new Locker(Type.L, 10)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(singletonList(smallLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));
        Bag mediumBag = new Bag(Type.M);

        lockerRobotManager.save(mediumBag);
    }

    @Test
    public void should_bag_in_super_locker_robot_and_return_large_ticket_when_save_large_bag_given_manager_manage_1_small_locker_and_1_primary_locker_robot_and_1_super_locker_robot_and_all_are_full() {
        Locker smallLocker = new Locker(Type.S, 10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(new Locker(Type.M, 10)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(new Locker(Type.L, 10)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(singletonList(smallLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));
        Bag largeBag = new Bag(Type.L);

        Ticket largeTicket = lockerRobotManager.save(largeBag);

        assertEquals(largeBag, superLockerRobot.take(largeTicket));
        assertNotNull(largeTicket);
        assertEquals(Type.L, largeTicket.getType());
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_save_large_bag_given_manager_manage_1_small_locker_and_1_primary_locker_robot_are_not_full_but_super_locker_robot_is_full() {
        Locker smallLocker = new Locker(Type.S, 10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(new Locker(Type.M, 10)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(new Locker(Type.L, 1)));
        superLockerRobot.save(new Bag(Type.L));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(singletonList(smallLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));
        Bag largeBag = new Bag(Type.L);

        lockerRobotManager.save(largeBag);
    }

    @Test
    public void should_return_bag_when_take_bag_given_manager_manage_1_small_locker_and_1_primary_locker_robot_and_1_super_locker_robot_and_correct_small_ticket() {
        Locker smallLocker = new Locker(Type.S, 10);
        Bag smallBag = new Bag(Type.S);
        Ticket smallTicket = smallLocker.save(smallBag);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(new Locker(Type.M, 10)));
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(new Locker(Type.L, 10)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(singletonList(smallLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag takenBag = lockerRobotManager.take(smallTicket);

        assertEquals(smallBag, takenBag);
    }


    @Test
    public void should_return_bag_when_take_bag_given_manager_manage_1_small_locker_and_1_primary_locker_robot_and_1_super_locker_robot_and_correct_medium_ticket() {
        Locker smallLocker = new Locker(Type.S, 10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(new Locker(Type.M, 10)));
        Bag mediumBag = new Bag(Type.M);
        Ticket mediumTicket = primaryLockerRobot.save(mediumBag);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(new Locker(Type.L, 10)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(singletonList(smallLocker), singletonList(primaryLockerRobot), singletonList(superLockerRobot));

        Bag takenBag = lockerRobotManager.take(mediumTicket);

        assertEquals(mediumBag, takenBag);
    }
}
