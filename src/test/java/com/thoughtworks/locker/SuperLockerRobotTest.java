package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.Type;
import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.TicketInvalidException;
import com.thoughtworks.locker.robot.SuperLockerRobot;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SuperLockerRobotTest {
    @Test
    public void should_return_large_ticket_when_save_bag_given_robot_manage_1_locker_with_free_capacity() {
        Locker largeLocker = initLocker(Type.L, 10, 10);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(largeLocker));
        Bag largeBag = new Bag(Type.L);

        Ticket largeTicket = superLockerRobot.save(largeBag);

        assertNotNull(largeTicket);
        assertEquals(Type.L, largeTicket.getType());
    }

    @Test
    public void should_bag_in_2nd_locker_and_return_large_ticket_when_save_bag_given_robot_manage_1st_locker_with_10_total_5_free_capacity_and_2nd_with_5_total_3_free_capacity() {
        Locker firstLargeLocker = initLocker(Type.L, 10, 5);
        Locker secondLargeLocker = initLocker(Type.L, 5, 3);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(asList(firstLargeLocker, secondLargeLocker));
        Bag largeBag = new Bag(Type.L);

        Ticket largeTicket = superLockerRobot.save(largeBag);

        assertNotNull(largeTicket);
        assertEquals(Type.L, largeTicket.getType());
        assertEquals(largeBag, secondLargeLocker.take(largeTicket));
    }

    @Test
    public void should_bag_in_1st_locker_and_return_large_ticket_when_save_bag_given_robot_manage_1st_locker_with_10_total_5_free_capacity_and_2nd_with_8_total_4_free_capacity() {
        Locker firstLargeLocker = initLocker(Type.L, 10, 5);
        Locker secondLargeLocker = initLocker(Type.L, 8, 4);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(asList(firstLargeLocker, secondLargeLocker));
        Bag largeBag = new Bag(Type.L);

        Ticket largeTicket = superLockerRobot.save(largeBag);

        assertNotNull(largeTicket);
        assertEquals(Type.L, largeTicket.getType());
        assertEquals(largeBag, firstLargeLocker.take(largeTicket));
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_save_bag_given_robot_manage_both_of_lockers_are_full() {
        Locker firstLargeLocker = initLocker(Type.L, 1, 0);
        Locker secondLargeLocker = initLocker(Type.L, 1, 0);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(asList(firstLargeLocker, secondLargeLocker));
        Bag largeBag = new Bag(Type.L);

        superLockerRobot.save(largeBag);
    }

    @Test
    public void should_return_bag_when_take_bag_given_robot_manage_1_locker_and_large_ticket() {
        Locker largeLocker = initLocker(Type.L, 10, 10);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(largeLocker));
        Bag largeBag = new Bag(Type.L);
        Ticket largeTicket = superLockerRobot.save(largeBag);

        Bag takenBag = superLockerRobot.take(largeTicket);

        assertEquals(largeBag, takenBag);
    }

    @Test(expected = TicketInvalidException.class)
    public void should_throw_ticket_invalid_exception_when_take_bag_given_robot_manage_1_locker_and_invalid_large_ticket() {
        Locker largeLocker = initLocker(Type.L, 10, 10);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(largeLocker));
        superLockerRobot.save(new Bag(Type.L));
        Ticket invalidLargeTicket = new Ticket(Type.L);

        superLockerRobot.take(invalidLargeTicket);
    }

    private Locker initLocker(Type type, int capacity, int freeCapacity) {
        Locker locker = new Locker(type, capacity);
        for (int i = capacity; i > freeCapacity; i--) {
            locker.save(new Bag(type));
        }
        return locker;
    }
}
