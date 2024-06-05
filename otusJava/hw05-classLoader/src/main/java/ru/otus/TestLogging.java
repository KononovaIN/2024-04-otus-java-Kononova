package ru.otus;

import ru.otus.annotation.Log;

public class TestLogging implements TestLoggingInterface {
    @Override
    @Log
    public void calculation(int param) {
    }

    @Override
    @Log
    public void calculation(int param1, int param2) {

    }

    @Override
    @Log
    public void calculation(int param1, int param2, String param3) {

    }
}
