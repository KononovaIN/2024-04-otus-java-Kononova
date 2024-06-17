package ru.otus;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.loadBanknotes(5, 100);
        atm.loadBanknotes(5, 200);
        atm.loadBanknotes(5, 500);
        atm.loadBanknotes(1, 1000);
        atm.loadBanknotes(1, 2000);
        atm.loadBanknotes(1, 5000);

        System.out.println(atm.getAmount(10_000));
        System.out.println(atm);
    }
}
