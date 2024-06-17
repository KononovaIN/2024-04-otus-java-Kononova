package ru.otus.utils;

import lombok.experimental.UtilityClass;
import ru.otus.exceptions.InvalidParameterException;

@UtilityClass
public class Utils {
    public void checkAmount(int amount, int denomination) {
        if (amount < 0) {
            throw new InvalidParameterException("Amount must be greater than 0");
        }

        if (amount % denomination != 0) {
            throw new InvalidParameterException("It is impossible to receive the amount with the requested banknotes");
        }
    }
}
