package com.edwise.pocs;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private App app;

    @Before
    public void setUp() {
        this.app = new App();
    }

    @Test
    public void testApp() {
        String message = "key__CLICKS__23";

        Number number = this.app.method(message);

        assertThat(number).isEqualTo(23L);
    }

    @Test
    public void testApp2() {
        Number first = 23;
        String message = "key__CLICKS__" + first;

        Number number = this.app.method(message);

        assertThat(number).isEqualTo(23L);
    }
}
