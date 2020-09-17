package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.Type;
import com.thoughtworks.locker.exception.FullCapacityException;
import com.thoughtworks.locker.exception.IncorrectTicketTypeException;
import com.thoughtworks.locker.exception.TicketInvalidException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LockerTest {
    @Test
    public void should_return_small_ticket_when_save_bag_given_small_locker_with_free_capacity_and_small_bag() {
        Locker smallLocker = new Locker(Type.S, 10);
        Bag smallBag = new Bag(Type.S);

        Ticket smallTicket = smallLocker.save(smallBag);

        assertNotNull(smallTicket);
        assertEquals(Type.S, smallTicket.getType());
    }

    @Test(expected = FullCapacityException.class)
    public void should_throw_full_capacity_exception_when_save_bag_given_small_locker_is_full_and_small_bag() {
        Locker smallLocker = new Locker(Type.S, 1);
        smallLocker.save(new Bag(Type.S));
        Bag smallBag = new Bag(Type.S);

        smallLocker.save(smallBag);
    }

    @Test
    public void should_return_small_bag_when_take_bag_given_small_locker_and_small_ticket() {
        Locker smallLocker = new Locker(Type.S, 10);
        Bag smallBag = new Bag(Type.S);
        Ticket smallTicket = smallLocker.save(smallBag);

        Bag takenBag = smallLocker.take(smallTicket);

        assertEquals(smallBag, takenBag);
    }

    @Test(expected = TicketInvalidException.class)
    public void should_throw_ticket_invalid_exception_when_take_bag_given_small_locker_and_invalid_small_ticket() {
        Locker smallLocker = new Locker(Type.S, 10);
        smallLocker.save(new Bag(Type.S));
        Ticket invalidSmallTicket = new Ticket(Type.S);

        smallLocker.take(invalidSmallTicket);
    }

    @Test(expected = IncorrectTicketTypeException.class)
    public void should_throw_incorrect_ticket_type_exception_when_take_bag_given_small_locker_and_not_small_ticket() {
        Locker smallLocker = new Locker(Type.S, 10);
        smallLocker.save(new Bag(Type.S));
        Ticket mediumTicket = new Ticket(Type.M);

        smallLocker.take(mediumTicket);
    }
}
