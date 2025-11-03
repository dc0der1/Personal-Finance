package models;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {

    private LocalDate date;
    private String name;
    private int amount;
    private TransactionType transactionType;
    private final UUID id;

    public Transaction(LocalDate date, String name, int amount) {
        this.date = date;
        this.name = name;
        this.amount = amount;
        this.id = UUID.randomUUID();
    }

    // Had to make another constructor for reading the transaction type because when I did list transactions,
    // The type kept returning null
    public Transaction(LocalDate date, String name, int amount, TransactionType transactionType) {
        this.date = date;
        this.name = name;
        this.amount = amount;
        this.transactionType = transactionType;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Date: " + this.date +
                "\nName: " + this.name +
                "\nAmount: " + this.amount +
                "\nType: " + this.transactionType +
                "\nID: " + this.id;
    }

    public TransactionType getTransactionType() { return transactionType; }
    public LocalDate getDate() { return date; }
    public String getName() { return name; }
    public int getAmount() { return amount; }
    public UUID getId() { return id; }
    public void setTransactionType(TransactionType transactionType) { this.transactionType = transactionType; }
}
