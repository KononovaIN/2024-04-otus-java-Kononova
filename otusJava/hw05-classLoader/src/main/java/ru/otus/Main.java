package ru.otus;

import ru.otus.proxy.ProxyLogging;

public class Main {
    public static void main(String[] args) {
        TestLoggingInterface test = ProxyLogging.createTestLogging();
        test.calculation(6);
        test.calculation(7, 8);
        test.calculation(9, 10, "Tadam");
    }
}
