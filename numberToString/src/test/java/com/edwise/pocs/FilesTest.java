package com.edwise.pocs;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 02/11/2017.
 */
public class FilesTest {

    @Test
    public void testFileDir() {
        File file = new File("/tmp/user_ids_reports/");
        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void testFileWrite() throws IOException {
//        Path tempDirectory = Files.createTempDirectory("tests");
//        Path destinationPath = Paths.get(tempDirectory.toString(), "fileName.txt");
//        Files.write(destinationPath, (Iterable<String>) Stream.of("Line 1", "Line 2")::iterator,
//                    StandardOpenOption.WRITE,
//                    StandardOpenOption.CREATE);

        List<String> sizes = Arrays.asList();

        sizes.add("new");

        System.out.println(sizes);
    }

    @Test
    public void testFileRead() throws IOException {
        Path path = Paths.get("tasksLog.log");

        logMemory();
        try (Stream<String> fileStream = Files.lines(path)) {
            System.out.println(fileStream.count());
            logMemory();
        }
    }

    @Test
    public void testFileReadApacheCommons() throws IOException {
        Path path = Paths.get("tasksLog.log");

        System.out.println("First log: ");
        logMemory();
        LineIterator it = FileUtils.lineIterator(path.toFile());
        long count = 0;
        try {
            while (it.hasNext()) {
                it.nextLine();
                count++;
            }
            System.out.println("Second log: ");
            logMemory();
        } finally {
            LineIterator.closeQuietly(it);
        }
        System.out.println(count);
    }


    private final void logMemory() {
        System.out.println("Max Memory: Mb " + Runtime.getRuntime()
                                                .maxMemory() / 1048576);
        System.out.println("Total Memory: Mb " + Runtime.getRuntime()
                                                  .totalMemory() / 1048576);
        System.out.println("Free Memory: Mb " + Runtime.getRuntime()
                                                 .freeMemory() / 1048576);
    }
}
