package com.edwise.pocs;

import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    private static final String MESSAGE_SEPARATOR = "__";

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Integer test = null;

        try {
            if (test >= 123) {
                System.out.println("vamos!!");
            }
            else {
                System.out.println("wow!!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }

//    public void testLogs() {
//        log.info("Mi mensaje {}", 124);
//    }

    public Number method(String message) {
        System.out.println("PUB message: " + message);
        String[] pieces = message.split(MESSAGE_SEPARATOR);
        try {
            System.out.println(pieces[0]);
            System.out.println(pieces[1]);
            Number number = NumberFormat.getInstance().parse(pieces[2]);
            System.out.println(number);
            return number;
        } catch (Exception e) {
            System.out.println("Error parsing message from progress redis channel: " + Arrays.toString(pieces));
            System.out.println(e);
        }
        return 0;
    }
}
