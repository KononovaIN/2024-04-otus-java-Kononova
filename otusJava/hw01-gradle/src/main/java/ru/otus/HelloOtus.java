package ru.otus;

import com.google.common.base.Splitter;

import java.util.List;

public class HelloOtus {
    public static void main(String[] args) {
        String info = "Irina Kononova 23";
        Splitter splitter = Splitter.on(" ");
        List<String> infoFields = splitter.splitToList(info);

        Person person = new Person(infoFields.get(0), infoFields.get(1), Integer.parseInt(infoFields.get(2)));
        System.out.println(person);
    }
}
