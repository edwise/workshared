package com.edwise.pocs;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;



/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 17/10/2017.
 */
public class JodaDateFormatterTest {

    @Test
    public void testJodaDateFormatter() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYYMMddHHmmZ");
        DateTime dateTime =  DateTime.parse("2016-10-31T00:00:00.000+02:00");
        DateTimeZone timezone = DateTimeZone.forID("Europe/Madrid");

        String formated = dateTimeFormatter.withZone(timezone).print(dateTime);

        System.out.println(dateTime); // Esto muestra: 2016-10-31T00:00:00.000+02:00
        System.out.println(formated); // Esto muestra: 20161030
    }
}
