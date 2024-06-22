package ru.otus.processor.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("ProcessorEvenSecondException должен")
class ProcessorEvenSecondExceptionTest {

    @Test
    @DisplayName("Выкидывать исключение на четной секунде")
    void processEventSecond() throws InterruptedException {
        var message = new Message.Builder(12L)
                .field1("field1")
                .build();

        var processor = new ProcessorEvenSecondException();

        int second = LocalDateTime.now().getSecond();
        if (second % 2 != 0) {
            Thread.sleep(1000);
        }

        assertThatThrownBy(() -> processor.process(message))
                .isNotNull();
    }

    @Test
    @DisplayName("Возвращать результат на нечетной секунде")
    void processReturnNotEvenSecond() throws InterruptedException {
        var message = new Message.Builder(12L)
                .field1("field1")
                .build();

        var processor = new ProcessorEvenSecondException();

        int second = LocalDateTime.now().getSecond();
        if (second % 2 == 0) {
            Thread.sleep(1000);
        }

        assertThat(message).isEqualTo(processor.process(message));
    }
}