package com.thoughtworks.locker;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SmallLockerTest {
    @Test
    public void should_return_small_ticket_when_save_bag_given_small_locker_with_free_capacity_and_small_bag() {
        SmallLocker smallLocker = new SmallLocker(10);
        SmallBag smallBag = new SmallBag();

        SmallTicket smallTicket = smallLocker.save(smallBag);
        assertNotNull(smallTicket);
    }
}
