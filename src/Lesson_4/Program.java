package Lesson_4;

import Lesson_4.Exception.InsufficientFundsException;

public class Program {
    public static void main(String[] args) {
        try {
            Account account = new Account(50000);  //Создание аккаунта с балансом
//            Account testException = new Account(-150); //Тест для вызова обработки исключений
            System.out.println("\nИнициализация аккаунта: \n" +  account + "\n");

            account.deposit_amount(190); //Депозит
//            account.deposit_amount(-150); //Тест для вызова обработки исключений
            System.out.println("Баланс после депозита: \n"+account + "\n");

            account.withdrawals(500); //Снятие средств
//            account.withdrawals(500000); //Тест для вызова обработки исключений
            System.out.println("Баланс после снятия средств: \n"+account + "\n");

        }catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
        catch (InsufficientFundsException e) {
            System.out.println("Ошибка снятия средств: " + e.getMessage() + e.getBalance());
        }

        try {
            //Инициализируем 2 счета.
            Account sender = new DebitAccount(15000);
            Account getter = new DebitAccount(0);
            System.out.println("\nИнициализация аккаунтов: \n" + "Отправитель: " + sender + "\n"+
                    "Получатель: "+getter+"\n");
            //Делаем перевод средств с одного счета на другой.
            Transaction.tranfer(sender, getter,13000);
            System.out.println("\nБаланс после перевода: \n" + "Отправитель: " + sender + "\n"+
                    "Получатель: "+getter+"\n");

        }catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
        catch (InsufficientFundsException e) {
            System.out.println("Ошибка снятия средств: " + e.getMessage() + e.getBalance());
        }
    }
}
