package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.Type;
import org.junit.Test;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SuperLockerRobotTest {
    @Test
    public void should_return_large_ticket_when_save_bag_given_robot_manage_1_locker_with_free_capacity() {
        Locker largeLocker = new Locker(Type.L, 10);
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(singletonList(largeLocker));
        Bag largeBag = new Bag(Type.L);
        Ticket largeTicket = superLockerRobot.save(largeBag);

        assertNotNull(largeTicket);
        assertEquals(Type.L, largeTicket.getType());
    }
}
