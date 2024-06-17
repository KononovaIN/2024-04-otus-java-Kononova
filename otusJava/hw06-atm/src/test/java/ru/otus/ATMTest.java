package ru.otus;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.exceptions.BusinessException;
import ru.otus.money.Denomitations;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ATMTest {
    private static final ATM atm = new ATM();

    @BeforeAll
    static void setUp(){
        atm.loadBanknotes(5, 100);
        atm.loadBanknotes(1, 200);
        atm.loadBanknotes(1, 500);
        atm.loadBanknotes(1, 1000);
        atm.loadBanknotes(1, 2000);
        atm.loadBanknotes(1, 5000);
    }

    @Test
    void getAmount5000() {
        Map<Denomitations, Integer> expected = new HashMap<>();
        expected.put(Denomitations.FIVE_THOUSAND, 1);

        assertThat(expected).isEqualTo(atm.getAmount(5_000));
    }

    @Test
    void getAmount2000() {
        Map<Denomitations, Integer> expected = new HashMap<>();
        expected.put(Denomitations.TWO_THOUSAND, 1);

        assertThat(expected).isEqualTo(atm.getAmount(2_000));
    }

    @Test
    void getAmount1000() {
        Map<Denomitations, Integer> expected = new HashMap<>();
        expected.put(Denomitations.THOUSAND, 1);

        assertThat(expected).isEqualTo(atm.getAmount(1_000));
    }

    @Test
    void getAmount500() {
        Map<Denomitations, Integer> expected = new HashMap<>();
        expected.put(Denomitations.FIVE_HUNDRED, 1);

        assertThat(expected).isEqualTo(atm.getAmount(500));
    }

    @Test
    void getAmount200() {
        Map<Denomitations, Integer> expected = new HashMap<>();
        expected.put(Denomitations.TWO_HUNDRED, 1);

        assertThat(expected).isEqualTo(atm.getAmount(200));
    }

    @Test
    void getAmount500By100() {
        Map<Denomitations, Integer> expected = new HashMap<>();
        expected.put(Denomitations.HUNDRED, 5);

        assertThat(expected).isEqualTo(atm.getAmount(500));
    }

    @Test
    void getAmount10000() {
        ATM atm1 = new ATM();

        atm1.loadBanknotes(5, 100);
        atm1.loadBanknotes(5, 200);
        atm1.loadBanknotes(5, 500);
        atm1.loadBanknotes(3, 1000);
        atm1.loadBanknotes(3, 2000);
        atm1.loadBanknotes(1, 5000);

        Map<Denomitations, Integer> expected = new HashMap<>();
        expected.put(Denomitations.FIVE_THOUSAND, 1);
        expected.put(Denomitations.TWO_THOUSAND, 2);
        expected.put(Denomitations.THOUSAND, 1);

        assertThat(expected).isEqualTo(atm1.getAmount(10_000));
    }

    @Test
    void getAmountExpectedException() {
        ATM atm1 = new ATM();

        atm1.loadBanknotes(1, 100);
        atm1.loadBanknotes(0, 200);
        atm1.loadBanknotes(0, 500);
        atm1.loadBanknotes(0, 1000);
        atm1.loadBanknotes(0, 2000);
        atm1.loadBanknotes(0, 5000);

        assertThatThrownBy(() -> atm1.getAmount(100000))
                .isInstanceOf(BusinessException.class);
    }
}