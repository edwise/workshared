package com.edwise.pocs;

import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 01/06/17.
 */
public class OptionalPlayTest {

    @Data
    class MyBean {
        private String myProperty;
    }

    @Test
    public void testOptionalWithNull() {
        Map<String, MyBean> myBeanMap = new HashMap<>();
        MyBean myBean = new MyBean();
        myBean.setMyProperty("hola");
        myBeanMap.put("ias", myBean);

        Optional<String> myOptional = Optional.ofNullable(myBeanMap)
                                              .map(myMap -> myMap.get("ias"))
                                              .map(MyBean::getMyProperty);

        myOptional.ifPresent(System.out::println);

        assertThat(myOptional).isEmpty();
    }
}
