package ru.otus;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Person {
    private String name;
    private String secondName;
    private int age;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("Name", name)
                .add("Second name", secondName)
                .add("Age", age)
                .toString();
    }
}
