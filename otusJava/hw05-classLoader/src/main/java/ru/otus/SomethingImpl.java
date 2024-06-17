package ru.otus;

import ru.otus.annotation.Log;

public class SomethingImpl implements Something {
    @Override
    @Log
    public void foo() {

    }

    @Override
    @Log
    public void foo(double param) {

    }
}
