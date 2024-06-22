package ru.otus.listener.homework;

import ru.otus.listener.Listener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    private final Deque<Message> messages = new ArrayDeque<>();

    @Override
    public void onUpdated(Message msg) {
        var field13Data = msg.getField13().getData();
        var newFiled13 = new ObjectForMessage();
        newFiled13.setData(field13Data.stream().toList());

        Message message = new Message.Builder(msg.getId())
                .field1(msg.getField1())
                .field2(msg.getField2())
                .field3(msg.getField3())
                .field4(msg.getField4())
                .field5(msg.getField5())
                .field6(msg.getField6())
                .field7(msg.getField7())
                .field8(msg.getField8())
                .field9(msg.getField9())
                .field10(msg.getField10())
                .field11(msg.getField11())
                .field12(msg.getField12())
                .field13(newFiled13)
                .build();

        messages.add(message);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return messages.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
    }
}
