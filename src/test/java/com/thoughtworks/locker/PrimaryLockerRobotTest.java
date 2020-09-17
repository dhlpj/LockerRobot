package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.Type;
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
}
