package com.edwise.pocs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
import java.util.Properties;

/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 02/02/17.
 */
public class StringTest {

    private static final String LINE =
            "2017-02-01T13:00:57Z\t588f77d6d0e7f6183e000ce2\t{\"events\":{\"show\":0.01},\"adExchange\":\"smaato\",\"cid\":\"588f77d6d0e7f6183e000ce2\",\"lid\":\"588f77d6d0e7f6183e000d10\",\"creatURL\":\"https://ads.sonataplatform.com/ad/v2/588f77d6d0e7f6183e000ce2/588f7addd0e7f6183e000d7b\",\"creatSize\":\"320x480\",\"creatId\":\"588f7addd0e7f6183e000d7b\",\"creatType\":\"banner\",\"lat\":-12.067795,\"lng\":-77.0886,\"bidid\":\"smaato.DVYCCGMBDJ\",\"adf\":\"js\",\"winPrice\":2.0,\"bidPrice\":2.0,\"maxPriceInUserCurrency\":10.0,\"avoidShowTrk\":\"false\",\"geohash\":\"6mc5mg1\",\"platform\":\"sonata\",\"owner\":\"5852b31a16aa7df7180008f2\",\"site\":\"Nine Manga (NEW)_Nine Manga - 320x480_MobileWebsite_IAB_FULL_SCREEN_320x480_IAB1 (www.ninemanga.com)\",\"userGender\":\"unknown\",\"userAge\":-1,\"deviceOs\":\"android\",\"deviceMake\":\"lenovo\",\"deviceModel\":\"a6020137\",\"deviceCarrier\":\"unknown\",\"deviceLanguage\":\"unknown\",\"country\":\"per\",\"geoType\":\"ip\",\"geoFetch\":false,\"enrichments\":{\"geoip\":\"no\"},\"userTrackingLegal\":true,\"appType\":\"site\",\"appName\":\"nine manga (new)_nine manga - 320x480_mobilewebsite_iab_full_screen_320x480_iab1 (www.ninemanga.com)\",\"appNameUpdated\":\"nine manga (new)_nine manga - 320x480_mobilewebsite_iab_full_screen_320x480_iab1 (www.ninemanga.com)\",\"fullUrl\":\"Nine Manga (NEW)_Nine Manga - 320x480_MobileWebsite_IAB_FULL_SCREEN_320x480_IAB1 (www.ninemanga.com)\",\"publisherId\":\"1100002098\",\"publisherName\":\"Taadd\",\"impressionTagId\":\"130180553\",\"appCategories\":[\"IAB1\"],\"typeName\":\"show\",\"location\":{\"lat\":-12.067795,\"lon\":-77.0886},\"locationUpdated\":{\"lat\":-12.067795,\"lon\":-77.0886},\"inventoryItemKey\":\"120070997::www.ninemanga.com\",\"channelIds\":[\"NO_CHANNEL\"],\"ts\":1485954057892,\"key\":\"smaato.DVYCCGMBDJ\",\"cost\":0.01,\"invalid\":false,\"ltype\":\"circle\",\"tag\":\"588f77d6d0e7f6183e000ce2\",\"time\":\"2017-02-01T13:00:57Z\"}";

    @Test
    public void testStringSubStringWithIndexOf() {

        Properties properties = System.getProperties();
        properties.list(System.out);

        Map<String, String> env = System.getenv();
        env.forEach((k, v) -> System.out.println("key: " + k + " value: " + v));
        if (env.containsKey("COMPUTERNAME")) {
            System.out.println(env.get("COMPUTERNAME"));
        } else if (env.containsKey("HOSTNAME")) {
            System.out.println(env.get("HOSTNAME"));
        } else {
            System.out.println("Unknown Computer");
        }
    }

    @Test
    public void testLogs() {
//        log.info("Mi mensaje {}", 124);
    }
}
