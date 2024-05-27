package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.exceptions.ReflectionException;
import ru.otus.statistic.Statistic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import static ru.otus.statistic.StatusTest.ERROR;
import static ru.otus.statistic.StatusTest.SUCCESS;

public class TestStarter {

    public static Statistic start(String fullClassName) throws ReflectionException {
        try {
            Statistic statistic = new Statistic();

            Class<?> clazz = Class.forName(fullClassName);
            Method[] methods = clazz.getMethods();

            List<Method> before = getMethodByAnnotation(methods, Before.class);
            List<Method> after = getMethodByAnnotation(methods, After.class);
            List<Method> tests = getMethodByAnnotation(methods, Test.class);

            for (var test : tests) {
                Object o = getInstance(clazz);

                try {
                    invoke(before, o);
                    test.invoke(o);

                    statistic.getResult().put(test.getName(), SUCCESS);
                } catch (Exception e) {
                    statistic.getResult().put(test.getName(), ERROR);
                } finally {
                    invoke(after, o);
                }
            }

            return statistic;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new ReflectionException(e);
        }
    }

    private static List<Method> getMethodByAnnotation(Method[] methods, Class<? extends Annotation> annotationClass) {
        List<Method> listMethods = new LinkedList<>();

        for (var method : methods) {
            Annotation annotation = method.getAnnotation(annotationClass);

            if (annotation != null) {
                listMethods.add(method);
            }
        }

        return listMethods;
    }

    private static Object getInstance(Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor<?> constructor = clazz.getConstructor();
        return constructor.newInstance();
    }

    private static void invoke(List<Method> methods, Object o) throws InvocationTargetException, IllegalAccessException {
        for (Method m : methods) {
            m.invoke(o);
        }
    }
}
