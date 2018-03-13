package com.edwise.pocs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;


/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 03/05/17.
 */
@RunWith(Parameterized.class)
public class IntStreamTest {

    @Parameters
    public static Object[] data() {
        return new Object[] {
                "6442d7a3-a793-4138-9467-f2da6df28861",
                "455524409859021173",
                "WQmMOwAG1-wKUacriQf4ZA",
                "769497814826206580",
                "33b88b56-cebc-46cb-a661-3b8b9920b99f",
                "3eb589b8-e17e-460c-b671-6286addbe88c"};
    }

    @Parameter
    public String id;

    @Test
    public void intStreamShouldLikeMeXD() {
        System.out.println("Id: " + id);

        int delta = id.chars().skip(id.length() - 3).peek(System.out::println).sum();
        System.out.println(delta);

        long sum = (long) id.chars().sum() * delta;
        System.out.println(sum);

        assertThat(id).isNotEmpty();
    }
}
