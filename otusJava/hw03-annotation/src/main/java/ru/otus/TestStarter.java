package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.statistic.Statistic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import static ru.otus.statistic.StatusTest.ERROR;
import static ru.otus.statistic.StatusTest.SUCCESS;

public class TestStarter {

    public static Statistic start(String fullClassName) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Statistic statistic = new Statistic();

        Class<?> clazz = Class.forName(fullClassName);
        Method[] methods = clazz.getMethods();

        final String before = getMethodNameBefore(methods);
        final String after = getMethodNameAfter(methods);
        List<String> tests = getMethodsNameTest(methods);

        for (var test : tests) {
            Object o = getInstance(clazz);

            try {
                if (isCanInvoke(before)) {
                    clazz.getMethod(before).invoke(o);
                }

                clazz.getMethod(test).invoke(o);

                statistic.getResult().put(test, SUCCESS);
            } catch (Exception e) {
                statistic.getResult().put(test, ERROR);
            } finally {
                if (isCanInvoke(after)) {
                    clazz.getMethod(after).invoke(o);
                }
            }
        }

        return statistic;
    }

    private static String getMethodNameBefore(Method[] methods) {
        for (var method : methods) {
            Before annotation = method.getAnnotation(Before.class);

            if (annotation != null) {
                return method.getName();
            }
        }

        return "";
    }

    private static String getMethodNameAfter(Method[] methods) {
        for (var method : methods) {
            After annotation = method.getAnnotation(After.class);

            if (annotation != null) {
                return method.getName();
            }
        }

        return "";
    }

    private static List<String> getMethodsNameTest(Method[] methods) {
        List<String> tests = new LinkedList<>();
        for (var method : methods) {
            Test annotation = method.getAnnotation(Test.class);

            if (annotation != null) {
                tests.add(method.getName());
            }
        }

        return tests;
    }

    private static Object getInstance(Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor<?> constructor = clazz.getConstructor();
        return constructor.newInstance();
    }

    private static boolean isCanInvoke(String name){
        return !name.isEmpty();
    }
}
