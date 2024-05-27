package ru.otus.tests;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.exceptions.AssertException;

public class CalculationTest {
    private long result;
    private int a;
    private int b;

    @Before
    public void setUp() {
        a = 10;
        b = 5;

        System.out.println("Before test. Hash: " + Integer.toHexString(hashCode()));
    }

    @After
    public void tearDown() {
        result = 0;
        System.out.println("After test. Hash: " + Integer.toHexString(hashCode()));
    }

    @Test
    public void testMulty_ok() {
        System.out.println("Begin testMulty. Hash: " + Integer.toHexString(hashCode()));
        long expected = 50;
        result = a * b;

        if (expected != result) {
            throw new AssertException();
        }
    }

    @Test
    public void testPlus_nok() {
        System.out.println("Begin testPlus. Hash: " + Integer.toHexString(hashCode()));
        long expected = 50;
        result = a + b;

        if (expected != result) {
            throw new AssertException();
        }
    }

    @Test
    public void testMinus_ok() {
        System.out.println("Begin testMinus. Hash: " + Integer.toHexString(hashCode()));
        long expected = 5;
        result = a - b;

        if (expected != result) {
            throw new AssertException();
        }
    }

    @Test
    public void testDivision_nok() {
        System.out.println("Begin testDivision. Hash: " + Integer.toHexString(hashCode()));
        long expected = 5;
        result = a / b;

        if (expected != result) {
            throw new AssertException();
        }
    }
}
