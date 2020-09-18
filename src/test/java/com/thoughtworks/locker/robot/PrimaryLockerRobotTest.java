package com.thoughtworks.locker.robot;

import com.thoughtworks.locker.Bag;
import com.thoughtworks.locker.Locker;
import com.thoughtworks.locker.Ticket;
import com.thoughtworks.locker.enums.Type;
import com.thoughtworks.locker.exception.ConfigurationErrorException;
import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.IncorrectTicketTypeException;
import com.thoughtworks.locker.exception.TicketInvalidException;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
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

    @Test
    public void should_return_bag_when_take_bag_given_correct_medium_ticket() {
        Locker mediumLocker = new Locker(Type.M, 10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(mediumLocker));
        Bag mediumBag = new Bag(Type.M);
        Ticket mediumTicket = primaryLockerRobot.save(mediumBag);

        Bag takenBag = primaryLockerRobot.take(mediumTicket);

        assertEquals(mediumBag, takenBag);
    }

    @Test(expected = TicketInvalidException.class)
    public void should_throw_ticket_invalid_exception_when_take_bag_given_invalid_medium_ticket() {
        Locker mediumLocker = new Locker(Type.M, 10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(mediumLocker));
        primaryLockerRobot.save(new Bag(Type.M));
        Ticket invalidMediumTicket = new Ticket(Type.M);

        primaryLockerRobot.take(invalidMediumTicket);
    }

    @Test(expected = IncorrectTicketTypeException.class)
    public void should_throw_incorrect_ticket_type_exception_when_take_bag_given_not_medium_ticket() {
        Locker mediumLocker = new Locker(Type.M, 10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(singletonList(mediumLocker));
        primaryLockerRobot.save(new Bag(Type.M));
        Ticket smallTicket = new Ticket(Type.S);

        primaryLockerRobot.take(smallTicket);
    }

    @Test(expected = ConfigurationErrorException.class)
    public void should_throw_configuration_error_exception_when_configure_primary_locker_robot_given_robot_manage_not_medium_locker() {
        Locker smallLocker = new Locker(Type.S, 10);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(asList(smallLocker));
    }
}
