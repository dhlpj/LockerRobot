package com.thoughtworks.locker;

import com.thoughtworks.locker.enums.Type;

public class Ticket {
    private Type type;

    public Ticket(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
