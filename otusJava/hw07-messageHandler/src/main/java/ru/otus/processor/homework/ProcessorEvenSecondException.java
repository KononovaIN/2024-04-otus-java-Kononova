package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;

public class ProcessorEvenSecondException implements Processor {
    @Override
    public Message process(Message message) {
        int second = LocalDateTime.now().getSecond();

        if (second % 2 == 0) {
            throw new RuntimeException("Exception in even second");
        }

        return message;
    }
}
