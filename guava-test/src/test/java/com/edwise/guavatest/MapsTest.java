package com.edwise.guavatest;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.assertj.core.data.MapEntry.entry;

public class MapsTest {

    private Map<String, String> sourceMap;

    @Test
    public void testNewHashMapsWithNullThenThrowsNullPointerException() {
        sourceMap = null;

        Throwable thrown = catchThrowable(() -> {
            Map<String, String> map = Maps.newHashMap(sourceMap);
        });

        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testNewHashMapsWithValuesThenResultIsCorrect() {
        sourceMap = Maps.newHashMap();
        sourceMap.put("header1", "valueHeader1");
        sourceMap.put("header2", "valueHeader2");
        sourceMap.put("header3", "valueHeader3");

        Map<String, String> map = Maps.newHashMap(sourceMap);

        assertThat(map).hasSize(sourceMap.size())
                .contains(
                        entry("header1", "valueHeader1"),
                        entry("header2", "valueHeader2"),
                        entry("header3", "valueHeader3"));
    }

    @Test
    public void testNewHashMapsWithMapUnModificableThenResultIsCorrect() {
        Map<String, String> modificableMap = Maps.newHashMap();
        modificableMap.put("header1", "valueHeader1");
        modificableMap.put("header2", "valueHeader2");
        modificableMap.put("header3", "valueHeader3");
        sourceMap = Collections.unmodifiableMap(modificableMap);

        Map<String, String> map = Maps.newHashMap(sourceMap);

        assertThat(map).hasSize(sourceMap.size())
                .contains(
                        entry("header1", "valueHeader1"),
                        entry("header2", "valueHeader2"),
                        entry("header3", "valueHeader3"));
    }
}