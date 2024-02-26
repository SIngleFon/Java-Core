package Lesson_4;

import Lesson_4.Exception.InsufficientFundsException;

public class CreditAccount extends Account{
    public CreditAccount(int initial_balance) {
        super(initial_balance);
    }

    @Override
    public void deposit_amount(int deposit_amount) {
        super.deposit_amount(deposit_amount);
    }

    @Override
    public void withdrawals(int withdrawals) throws InsufficientFundsException {
        super.withdrawals(withdrawals);
    }

    @Override
    public double getInitial_balance() {
        return super.getInitial_balance();
    }
}
