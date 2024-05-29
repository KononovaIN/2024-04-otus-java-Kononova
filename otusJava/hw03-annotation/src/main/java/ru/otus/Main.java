package ru.otus;

import ru.otus.exceptions.ReflectionException;
import ru.otus.statistic.Statistic;

public class Main {
    public static void main(String[] args) throws ReflectionException {
        Statistic statistic = TestStarter.start("ru.otus.tests.CalculationTest");

        System.out.println(statistic);
    }
}