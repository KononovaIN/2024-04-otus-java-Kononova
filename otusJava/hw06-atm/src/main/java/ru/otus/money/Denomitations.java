package ru.otus.money;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Denomitations {
    HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    THOUSAND(1000),
    TWO_THOUSAND(2000),
    FIVE_THOUSAND(5000);

    private final int denomination;

    public static Denomitations getMinDenomination() {
        Denomitations min = HUNDRED;

        Denomitations[] values = Denomitations.values();
        for (Denomitations val : values) {
            if (val.getDenomination() < min.denomination) {
                min = val;
            }
        }

        return min;
    }

    @Override
    public String toString() {
        return String.valueOf(denomination);
    }
}
