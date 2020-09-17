package com.thoughtworks.locker;

import com.thoughtworks.locker.bag.MediumBag;
import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.ticket.MediumTicket;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MediumLockerTest {
    @Test
    public void should_return_medium_ticket_when_save_bag_given_medium_locker_with_free_capacity_and_medium_bag() {
        MediumLocker mediumLocker = new MediumLocker(10);
        MediumBag mediumBag = new MediumBag();

        MediumTicket mediumTicket = mediumLocker.save(mediumBag);

        assertNotNull(mediumTicket);
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_save_bag_given_medium_locker_is_full_and_medium_bag() {
        MediumLocker mediumLocker = new MediumLocker(1);
        mediumLocker.save(new MediumBag());
        MediumBag mediumBag = new MediumBag();

        mediumLocker.save(mediumBag);
    }
}