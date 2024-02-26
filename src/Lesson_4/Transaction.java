package Lesson_4;

import Lesson_4.Exception.InsufficientFundsException;

public class Transaction {
    public static void tranfer(Account send, Account get, int money) throws InsufficientFundsException {
        if (send == null || get == null){
            throw new IllegalArgumentException("Счет не может быть пустым");
        }if (money < 0){
            throw new IllegalArgumentException("Сумма перевода должна быть больше 0");
        }if(money > send.getInitial_balance()){
            throw new InsufficientFundsException("На балансе недостаточно средств. Баланс отправителя: ", send.getInitial_balance());
        }
        send.withdrawals(money);
        get.deposit_amount(money);

    }
}
