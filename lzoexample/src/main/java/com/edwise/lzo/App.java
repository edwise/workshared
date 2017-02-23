package com.edwise.lzo;

import org.anarres.lzo.LzopInputStream;

import java.io.*;

/**
 * Lzo example.
 */
public class App {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream(new File("/Users/eduardo.anton/Documents/zips/events.lzo"));

        LzopInputStream stream = new LzopInputStream(in);

        OutputStream outputStream =
                new FileOutputStream(new File("/Users/eduardo.anton/Documents/zips/eventsExtracted.csv"));
        int read;
        byte[] bytes = new byte[1024];
        while ((read = stream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        outputStream.close();
        stream.close();
    }
}
