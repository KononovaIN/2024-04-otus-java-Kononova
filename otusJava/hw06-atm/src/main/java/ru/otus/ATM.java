package ru.otus;

import ru.otus.money.Denominations;

import java.util.Map;

public interface ATM {
    void loadBanknotes(Denominations denomination, int count);

    Map<Denominations, Integer> getAmount(int amount);
}
