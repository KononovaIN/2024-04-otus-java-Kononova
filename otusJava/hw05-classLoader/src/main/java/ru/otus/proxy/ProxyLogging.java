package ru.otus.proxy;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.TestLoggingInterface;
import ru.otus.annotation.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProxyLogging {
    private static final Logger logger = LoggerFactory.getLogger(ProxyLogging.class);

    public static TestLoggingInterface create(TestLoggingInterface instance) {
        InvocationHandler handler = new ProxyInvocationHandler(instance);
        return (TestLoggingInterface)
                Proxy.newProxyInstance(instance.getClass().getClassLoader(),
                        instance.getClass().getInterfaces(),
                        handler);
    }

    @Builder
    @Getter
    @EqualsAndHashCode
    static class MethodInfo {
        String name;
        Object[] args;

        static MethodInfo buildMethodInfo(Method method) {
            return MethodInfo.builder()
                    .name(method.getName())
                    .args(method.getParameterTypes())
                    .build();
        }
    }


    static class ProxyInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testLoggingInterface;
        private final List<MethodInfo> methodsInfo;

        public ProxyInvocationHandler(TestLoggingInterface testLoggingInterface) {
            this.testLoggingInterface = testLoggingInterface;
            this.methodsInfo = findMethodsWithAnnotation(testLoggingInterface);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (methodsInfo.contains(MethodInfo.buildMethodInfo(method))) {
                logger.info("executed method:{}, param:{}", method.getName(), args);
            }

            return method.invoke(testLoggingInterface, args);
        }

        private List<MethodInfo> findMethodsWithAnnotation(TestLoggingInterface test) {
            return Arrays.stream(test.getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(Log.class))
                    .map(MethodInfo::buildMethodInfo)
                    .collect(Collectors.toList());
        }
    }
}
