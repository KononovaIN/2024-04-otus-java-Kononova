package ru.otus.proxy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.TestLogging;
import ru.otus.TestLoggingInterface;
import ru.otus.annotation.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProxyLogging {
    private static final Logger logger = LoggerFactory.getLogger(ProxyLogging.class);

    public static TestLoggingInterface createTestLogging() {
        InvocationHandler handler = new ProxyInvocationHandler(new TestLogging());
        return (TestLoggingInterface)
                Proxy.newProxyInstance(ProxyLogging.class.getClassLoader(), new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    @RequiredArgsConstructor
    static class ProxyInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testLoggingInterface;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isAnnotationPresent(method, Log.class)) {
                logger.info("executed method:{}, param:{}", method.getName(), args);
            }

            return method.invoke(testLoggingInterface, args);
        }

        private boolean isAnnotationPresent(Method method, Class<? extends Annotation> annotation) {
            return method.getAnnotation(annotation) != null;
        }
    }
}
