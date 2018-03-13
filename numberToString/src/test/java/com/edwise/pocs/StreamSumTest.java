package com.edwise.pocs;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 06/06/17.
 */
public class StreamSumTest {

    @Test
    public void testStreamToFloat() {
        Map<String, Float> ctrPerKPI = new HashMap<>();
        ctrPerKPI.put("First", 12.3F);
        ctrPerKPI.put("Second", 20.0F);
        ctrPerKPI.put("Third", 120.9F);


        float kpiTotalCTRPoints = Double.valueOf(ctrPerKPI.values()
                                                          .stream()
                                                          .mapToDouble(Float::doubleValue)
                                                          .sum()).floatValue();

        assertThat(kpiTotalCTRPoints).isCloseTo(153F, within(0.3F));
    }
}
