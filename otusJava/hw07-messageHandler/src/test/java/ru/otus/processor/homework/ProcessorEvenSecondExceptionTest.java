package ru.otus.processor.homework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;
import ru.otus.util.TimeProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("ProcessorEvenSecondException должен")
class ProcessorEvenSecondExceptionTest {

    @Test
    @DisplayName("Выкидывать исключение на четной секунде")
    void processEventSecond() {
        var timeProvider = mock(TimeProvider.class);
        when(timeProvider.getCurrentSeconds()).thenReturn(2);

        var message = new Message.Builder(12L)
                .field1("field1")
                .build();

        var processor = new ProcessorEvenSecondException(timeProvider);

        assertThatThrownBy(() -> processor.process(message))
                .isNotNull();
    }

    @Test
    @DisplayName("Возвращать результат на нечетной секунде")
    void processReturnNotEvenSecond() {
        var timeProvider = mock(TimeProvider.class);
        when(timeProvider.getCurrentSeconds()).thenReturn(3);

        var message = new Message.Builder(12L)
                .field1("field1")
                .build();

        var processor = new ProcessorEvenSecondException(timeProvider);

        assertThat(message).isEqualTo(processor.process(message));
    }
}