package com.edwise.pocs;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 16/05/17.
 */
public class AllOfFutureTest {

    @Test
    public void testCompletableFutureAllOFAndThen() {
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> sleepSeconds(2)),
                CompletableFuture.runAsync(() -> sleepSeconds(4)),
                CompletableFuture.runAsync(() -> sleepSeconds(1)));

        allOf.join();
        System.out.println("Todos terminados!");

    }

    private void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
            System.out.println("Ended!: " + seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
