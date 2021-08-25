package com.example.integration.model;

import java.util.Date;
import java.util.UUID;

public class Transaction implements Comparable<Transaction>{
    private final UUID uuid;
    private final boolean isSuccessful;
    private Date date;
    private final String message;

    public Transaction(UUID uuid, boolean isSuccessful, String message) {
        this.uuid = uuid;
        this.isSuccessful = isSuccessful;
        this.message = message;
        setDate();
    }

    public Transaction(boolean isSuccessful, String message) {
        this.uuid = UUID.randomUUID();
        this.isSuccessful = isSuccessful;
        this.message = message;
        setDate();
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public Date getDate() {
        return date;
    }

    private void setDate() {
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        this.date = new Date(System.currentTimeMillis());
    }

    @Override
    public int compareTo(Transaction o) {
        return o.getDate().compareTo(getDate());
    }
}
