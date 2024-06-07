package ru.otus.proxy;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static <T> T create(T instance) {
        InvocationHandler handler = new ProxyInvocationHandler<>(instance);
        return (T) Proxy.newProxyInstance(instance.getClass().getClassLoader(),
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


    static class ProxyInvocationHandler<T> implements InvocationHandler {
        private final T instance;
        private final List<MethodInfo> methodsInfo;

        public ProxyInvocationHandler(T instance) {
            this.instance = instance;
            this.methodsInfo = findMethodsWithAnnotation(instance);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (methodsInfo.contains(MethodInfo.buildMethodInfo(method))) {
                logger.info("executed method:{}, param:{}", method.getName(), args);
            }

            return method.invoke(instance, args);
        }

        private List<MethodInfo> findMethodsWithAnnotation(T instance) {
            return Arrays.stream(instance.getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(Log.class))
                    .map(MethodInfo::buildMethodInfo)
                    .collect(Collectors.toList());
        }
    }
}
