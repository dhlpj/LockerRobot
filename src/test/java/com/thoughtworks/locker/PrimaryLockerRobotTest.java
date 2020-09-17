package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.Type;
import com.thoughtworks.locker.exception.FullCapacityException;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PrimaryLockerRobotTest {
    @Test
    public void should_bag_in_1st_locker_with_free_capacity_and_return_medium_ticket_when_save_bag_given_robot_manage_2_locker() {
        Locker firstMediumLocker = new Locker(Type.M, 10);
        Locker secondMediumLocker = new Locker(Type.M, 10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(asList(firstMediumLocker, secondMediumLocker));
        Bag mediumBag = new Bag(Type.M);

        Ticket mediumTicket = primaryLockerRobot.save(mediumBag);

        assertNotNull(mediumTicket);
        assertEquals(Type.M, mediumTicket.getType());
        assertEquals(mediumBag, firstMediumLocker.take(mediumTicket));
    }

    @Test
    public void should_bag_in_1st_locker_and_return_medium_ticket_when_save_bag_given_robot_manage_1st_locker_with_free_capacity_2nd_is_full() {
        Locker firstMediumLocker = new Locker(Type.M, 10);
        Locker secondMediumLocker = new Locker(Type.M, 1);
        secondMediumLocker.save(new Bag(Type.M));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(asList(firstMediumLocker, secondMediumLocker));
        Bag mediumBag = new Bag(Type.M);

        Ticket mediumTicket = primaryLockerRobot.save(mediumBag);

        assertNotNull(mediumTicket);
        assertEquals(Type.M, mediumTicket.getType());
        assertEquals(mediumBag, firstMediumLocker.take(mediumTicket));
    }

    @Test
    public void should_bag_in_2nd_locker_and_return_medium_ticket_when_save_bag_given_robot_manage_1st_locker_is_full_2nd_with_free_capacity() {
        Locker firstMediumLocker = new Locker(Type.M, 1);
        Locker secondMediumLocker = new Locker(Type.M, 10);
        firstMediumLocker.save(new Bag(Type.M));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(asList(firstMediumLocker, secondMediumLocker));
        Bag mediumBag = new Bag(Type.M);

        Ticket mediumTicket = primaryLockerRobot.save(mediumBag);

        assertNotNull(mediumTicket);
        assertEquals(Type.M, mediumTicket.getType());
        assertEquals(mediumBag, secondMediumLocker.take(mediumTicket));
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_save_bag_given_robot_manage_all_lockers_are_full() {
        Locker firstMediumLocker = new Locker(Type.M, 1);
        Locker secondMediumLocker = new Locker(Type.M, 1);
        firstMediumLocker.save(new Bag(Type.M));
        secondMediumLocker.save(new Bag(Type.M));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(asList(firstMediumLocker, secondMediumLocker));
        Bag mediumBag = new Bag(Type.M);

        primaryLockerRobot.save(mediumBag);
    }
}
