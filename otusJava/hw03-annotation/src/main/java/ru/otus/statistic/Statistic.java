package ru.otus.statistic;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Statistic {
    private Map<String, StatusTest> result;

    public Statistic() {
        result = new HashMap<>();
    }

    @Override
    public String toString() {
        return "All test count = " + result.size()
                + "\nResults: " + result;
    }
}
