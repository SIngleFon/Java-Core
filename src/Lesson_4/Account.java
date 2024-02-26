package Lesson_4;

import Lesson_4.Exception.InsufficientFundsException;

public class Account {
    private double initial_balance;
    public Account(int initial_balance) {
        if (initial_balance < 0){
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным");
        }
        this.initial_balance = initial_balance;
    }

    public  void deposit_amount(int deposit_amount){
        if(deposit_amount < 0){
            throw new IllegalArgumentException("Депозит не может быть отрицательным");
        }
        this.initial_balance += deposit_amount;
    }

    public void withdrawals(int withdrawals) throws InsufficientFundsException {
        if (withdrawals < 0) {
            throw new IllegalArgumentException("Сумма снятия не может быть отрицательной");
        }

        if (withdrawals > initial_balance) {
            throw new InsufficientFundsException("Недостаточно средств на счете. Текущий баланс: ", this.initial_balance);
        }

        this.initial_balance -= withdrawals;
    }


    public double getInitial_balance() {
        return initial_balance;
    }

    @Override
    public String toString() {
        return "Аккаунт[Баланс: " + initial_balance +']';
    }
}
