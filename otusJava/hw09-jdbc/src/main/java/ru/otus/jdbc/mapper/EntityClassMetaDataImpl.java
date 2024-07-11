package ru.otus.jdbc.mapper;

import ru.otus.annotation.Id;
import ru.otus.exceptions.ReflectionException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private final Class<T> actualClass;

    public EntityClassMetaDataImpl(Class<T> actualClass) {
        this.actualClass = actualClass;
    }

    @Override
    public String getName() {
        return actualClass.getSimpleName();
    }

    @Override
    public Constructor<T> getConstructor() {
        try {
            return actualClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new ReflectionException(e);
        }
    }

    @Override
    public Field getIdField() {
        for (var field : actualClass.getDeclaredFields()) {
            Id annotation = field.getAnnotation(Id.class);
            if (annotation != null) {
                return field;
            }
        }

        return null;
    }

    @Override
    public List<Field> getAllFields() {
        return Arrays.stream(actualClass.getDeclaredFields()).toList();
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return Arrays.stream(actualClass.getDeclaredFields())
                .filter(field -> !field.equals(getIdField()))
                .toList();
    }
}
