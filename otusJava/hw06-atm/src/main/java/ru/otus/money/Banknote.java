package ru.otus.money;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.AbstractMap;
import java.util.Map;

@AllArgsConstructor
@EqualsAndHashCode(exclude = "count")
public class Banknote {
    @Getter
    private int count;
    private final Denominations denomination;

    public void addBanknotes(int count) {
        this.count += count;
    }

    public Map.Entry<Denominations, Integer> getBanknotesByAmount(int amount) {
        int countBank = amount / denomination.getDenomination();
        int value;

        if (countBank > count) {
            value = count;
            count = 0;
        } else {
            value = countBank;
            this.count -= countBank;
        }

        return new AbstractMap.SimpleImmutableEntry<>(denomination, value);
    }

    public int getDenomination() {
        return denomination.getDenomination();
    }
}
