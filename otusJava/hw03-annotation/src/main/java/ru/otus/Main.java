package ru.otus;

import ru.otus.statistic.Statistic;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Statistic statistic = TestStarter.start("ru.otus.tests.CalculationTest");

        System.out.println(statistic);
    }
}