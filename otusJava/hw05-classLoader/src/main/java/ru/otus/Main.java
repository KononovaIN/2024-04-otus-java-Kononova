package ru.otus;

import ru.otus.proxy.ProxyLogging;

public class Main {
    public static void main(String[] args) {
        TestLogging test = ProxyLogging.create(new TestLoggingImpl());
        test.calculation(6);
        test.calculation(7, 8);
        test.calculation(9, 10, "Tadam");

        Something something = ProxyLogging.create(new SomethingImpl());
        something.foo();
        something.foo(23.8);
    }
}
