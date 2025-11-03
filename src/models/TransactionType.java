package models;

public enum TransactionType {
    EARNING,
    EXPENSE,
    NEITHER;

    public static TransactionType fromAmount(int amount) {
        if (amount > 0) {
            return EARNING;
        } else if (amount < 0) {
            return EXPENSE;
        } else {
            return NEITHER;
        }
    }
}
