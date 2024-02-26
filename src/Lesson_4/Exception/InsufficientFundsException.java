package Lesson_4.Exception;

public class InsufficientFundsException extends Exception {
    private double initial_balance;

    public InsufficientFundsException(String message, double initial_balance) {
        super(message);
        this.initial_balance = initial_balance;
    }

    public double getBalance() {
        return initial_balance;
    }
}
