package models;

import java.util.UUID;

public class Transaction {

    protected String date;
    protected String name;
    protected int amount;
    protected String id;

    public Transaction(String date, String name, int amount) {
        this.date = date;
        this.name = name;
        this.amount = amount;
        this.id = UUID.randomUUID().toString();
    }

    public Transaction() {}

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }
}
