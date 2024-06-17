package ru.otus.money;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.otus.exceptions.InvalidParameterException;

import java.util.AbstractMap;
import java.util.Map;

@AllArgsConstructor
@EqualsAndHashCode(exclude = "count")
public abstract class Banknote {
    @Getter
    private int count;
    private final Denomitations denomination;

    public void addBanknotes(int count) {
        this.count += count;
    }

    public Map.Entry<Denomitations, Integer> getBanknotesByAmount(int amount) {
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

    public int getDenomination(){
        return denomination.getDenomination();
    }
}
