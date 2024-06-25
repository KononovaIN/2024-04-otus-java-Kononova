package ru.otus.processor.homework;

import lombok.AllArgsConstructor;
import ru.otus.model.Message;
import ru.otus.processor.Processor;
import ru.otus.util.TimeProvider;
import ru.otus.util.TimeProviderImpl;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ProcessorEvenSecondException implements Processor {
    private TimeProvider timeProvider;

    @Override
    public Message process(Message message) {
        int second = timeProvider.getCurrentSeconds();

        if (second % 2 == 0) {
            throw new RuntimeException("Exception in even second");
        }

        return message;
    }
}
