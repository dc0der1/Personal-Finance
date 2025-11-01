package models;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {

    private LocalDate date;
    private String name;
    private int amount;
    private final UUID id;

    public Transaction(LocalDate date, String name, int amount) {
        this.date = date;
        this.name = name;
        this.amount = amount;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Date: " + this.date +
                "\nName: " + this.name +
                "\nAmount: " + this.amount +
                "\nID: " + this.id;
    }

    public LocalDate getDate() { return date; }
    public String getName() { return name; }
    public int getAmount() { return amount; }
    public UUID getId() { return id; }
}
