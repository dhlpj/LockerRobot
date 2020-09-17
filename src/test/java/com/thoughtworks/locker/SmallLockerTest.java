package com.thoughtworks.locker;

import com.thoughtworks.locker.bag.SmallBag;
import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.IncorrectTicketTypeException;
import com.thoughtworks.locker.exception.TicketInvalidException;
import com.thoughtworks.locker.ticket.MediumTicket;
import com.thoughtworks.locker.ticket.SmallTicket;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SmallLockerTest {
    @Test
    public void should_return_small_ticket_when_save_bag_given_small_locker_with_free_capacity_and_small_bag() {
        SmallLocker smallLocker = new SmallLocker(10);
        SmallBag smallBag = new SmallBag();

        SmallTicket smallTicket = smallLocker.save(smallBag);

        assertNotNull(smallTicket);
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_save_bag_given_small_locker_is_full_and_small_bag() {
        SmallLocker smallLocker = new SmallLocker(1);
        smallLocker.save(new SmallBag());
        SmallBag smallBag = new SmallBag();

        smallLocker.save(smallBag);
    }

    @Test
    public void should_return_small_bag_when_take_bag_given_small_locker_and_small_ticket() {
        SmallLocker smallLocker = new SmallLocker(10);
        SmallBag smallBag = new SmallBag();
        SmallTicket smallTicket = smallLocker.save(smallBag);

        SmallBag takenBag = smallLocker.take(smallTicket);

        assertEquals(smallBag, takenBag);
    }

    @Test(expected = TicketInvalidException.class)
    public void should_throw_ticket_invalid_exception_when_take_bag_given_small_locker_and_invalid_small_ticket() {
        SmallLocker smallLocker = new SmallLocker(10);
        smallLocker.save(new SmallBag());
        SmallTicket invalidSmallTicket = new SmallTicket();

        smallLocker.take(invalidSmallTicket);
    }

    @Test(expected = IncorrectTicketTypeException.class)
    public void should_throw_incorrect_ticket_type_exception_when_take_bag_given_small_locker_and_not_small_ticket() {
        SmallLocker smallLocker = new SmallLocker(10);
        smallLocker.save(new SmallBag());
        MediumTicket mediumTicket = new MediumTicket();

        smallLocker.take(mediumTicket);
    }
}
