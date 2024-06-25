package ru.otus.util;

import java.time.LocalDateTime;

public class TimeProviderImpl implements TimeProvider{
    @Override
    public int getCurrentSeconds() {
        return LocalDateTime.now().getSecond();
    }
}
