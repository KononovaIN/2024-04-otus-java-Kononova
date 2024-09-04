package ru.otus.protobuf;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings({"squid:S106", "squid:S2142"})
public class GRPCClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8190;

    public static void main(String[] args) throws InterruptedException {
        var channel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();

        var stub = RemoteServiceGrpc.newStub(channel);
        AtomicLong generatedValue = new AtomicLong();
        var latch = new CountDownLatch(1);

        stub.generate(RangeMessage.newBuilder()
                .setFirstValue(0)
                .setLastValue(30)
                .build(), new StreamObserver<ValueMessage>() {
            @Override
            public void onNext(ValueMessage value) {
                System.out.println("Get value from server: " + value.getValue());
                generatedValue.set(value.getValue());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println(t);
            }

            @Override
            public void onCompleted() {
                System.out.println("\n\nDone!");
                latch.countDown();
            }
        });

        long currentValue = 0;
        for (int i = 0; i <= 50; i++) {
            currentValue = currentValue + generatedValue.get() + 1;
            generatedValue.set(0);
            System.out.println("Current value: " + currentValue);

            Thread.sleep(1000);
        }

        latch.await();
        channel.shutdown();
    }
}
