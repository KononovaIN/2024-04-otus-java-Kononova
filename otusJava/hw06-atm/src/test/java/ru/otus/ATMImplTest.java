package ru.otus;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.exceptions.BusinessException;
import ru.otus.money.Denominations;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static ru.otus.money.Denominations.*;

class ATMImplTest {
    private static ATMImpl atm;

    @BeforeAll
    static void setUp(){
        Map<Denominations, Integer> denominations = new HashMap<>();
        denominations.put(HUNDRED, 5);
        denominations.put(TWO_HUNDRED, 1);
        denominations.put(FIVE_HUNDRED, 1);
        denominations.put(THOUSAND, 1);
        denominations.put(TWO_THOUSAND, 1);
        denominations.put(FIVE_THOUSAND, 1);

        atm = new ATMImpl(denominations);
    }

    @Test
    void getAmount5000() {
        Map<Denominations, Integer> expected = new HashMap<>();
        expected.put(Denominations.FIVE_THOUSAND, 1);

        assertThat(expected).isEqualTo(atm.getAmount(5_000));
    }

    @Test
    void getAmount2000() {
        Map<Denominations, Integer> expected = new HashMap<>();
        expected.put(Denominations.TWO_THOUSAND, 1);

        assertThat(expected).isEqualTo(atm.getAmount(2_000));
    }

    @Test
    void getAmount1000() {
        Map<Denominations, Integer> expected = new HashMap<>();
        expected.put(Denominations.THOUSAND, 1);

        assertThat(expected).isEqualTo(atm.getAmount(1_000));
    }

    @Test
    void getAmount500() {
        Map<Denominations, Integer> expected = new HashMap<>();
        expected.put(Denominations.FIVE_HUNDRED, 1);

        assertThat(expected).isEqualTo(atm.getAmount(500));
    }

    @Test
    void getAmount200() {
        Map<Denominations, Integer> expected = new HashMap<>();
        expected.put(Denominations.TWO_HUNDRED, 1);

        assertThat(expected).isEqualTo(atm.getAmount(200));
    }

    @Test
    void getAmount500By100() {
        Map<Denominations, Integer> expected = new HashMap<>();
        expected.put(Denominations.HUNDRED, 5);

        assertThat(expected).isEqualTo(atm.getAmount(500));
    }

    @Test
    void getAmount10000() {
        Map<Denominations, Integer> denominations = new HashMap<>();
        denominations.put(HUNDRED, 5);
        denominations.put(TWO_HUNDRED, 5);
        denominations.put(FIVE_HUNDRED, 5);
        denominations.put(THOUSAND, 3);
        denominations.put(TWO_THOUSAND, 3);
        denominations.put(FIVE_THOUSAND, 1);

        ATMImpl atm = new ATMImpl(denominations);

        Map<Denominations, Integer> expected = new HashMap<>();
        expected.put(Denominations.FIVE_THOUSAND, 1);
        expected.put(Denominations.TWO_THOUSAND, 2);
        expected.put(Denominations.THOUSAND, 1);

        assertThat(expected).isEqualTo(atm.getAmount(10_000));
    }

    @Test
    void getAmountExpectedException() {
        Map<Denominations, Integer> denominations = new HashMap<>();
        denominations.put(HUNDRED, 1);
        denominations.put(TWO_HUNDRED, 0);
        denominations.put(FIVE_HUNDRED, 0);
        denominations.put(THOUSAND, 0);
        denominations.put(TWO_THOUSAND, 0);
        denominations.put(FIVE_THOUSAND, 0);

        ATMImpl atm = new ATMImpl(denominations);

        assertThatThrownBy(() -> atm.getAmount(100000))
                .isInstanceOf(BusinessException.class);
    }
}