package ru.otus;

import ru.otus.money.Denominations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ru.otus.money.Denominations.*;

public class Main {
    public static void main(String[] args) {
        List<Denominations> denominations = new ArrayList<>();
        denominations.add(HUNDRED);
        denominations.add(TWO_HUNDRED);
        denominations.add(FIVE_HUNDRED);
        denominations.add(THOUSAND);
        denominations.add(TWO_THOUSAND);
        denominations.add(FIVE_THOUSAND);

        ATMImpl atmImpl = new ATMImpl(denominations);
        atmImpl.loadBanknotes(HUNDRED, 5);
        atmImpl.loadBanknotes(TWO_HUNDRED, 5);
        atmImpl.loadBanknotes(FIVE_HUNDRED, 5);
        atmImpl.loadBanknotes(THOUSAND, 1);
        atmImpl.loadBanknotes(TWO_THOUSAND, 1);

        System.out.println(atmImpl.getAmount(10_000));
        System.out.println(atmImpl);
    }
}
