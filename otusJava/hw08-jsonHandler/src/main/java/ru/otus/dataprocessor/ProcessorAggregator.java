package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        // группирует выходящий список по name, при этом суммирует поля value
        return new TreeMap<>(data.stream().collect(Collectors.toMap(Measurement::name, Measurement::value, Double::sum)));
    }
}
