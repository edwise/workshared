package com.edwise.utils.jsoncrosser;

import com.edwise.utils.jsoncrosser.model.BidderOwner;
import com.edwise.utils.jsoncrosser.model.MergedResult;
import com.edwise.utils.jsoncrosser.model.SonataCurrency;
import com.edwise.utils.jsoncrosser.model.SonataUser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class JsonCrosserApp {
    private static final String OWNERS_FILE_PATH = "src/resources/bidderOwners.json";
    private static final String USERS_FILE_PATH  = "src/resources/sonataUsers.json";

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        List<BidderOwner> bidderOwners =
                mapper.readValue(new File(OWNERS_FILE_PATH),
                                 mapper.getTypeFactory().constructCollectionType(List.class, BidderOwner.class));
        List<SonataUser> sonataUsers =
                mapper.readValue(new File(USERS_FILE_PATH),
                                 mapper.getTypeFactory().constructCollectionType(List.class, SonataUser.class));

        Map<String, SonataCurrency> sonataCurrencies =
                mapper.readValue(new File("src/resources/sonataCurrencies.json"),
                                 mapper.getTypeFactory().constructMapType(Map.class, String.class, SonataCurrency.class));

//        System.out.println("bidderOwners list size: " + bidderOwners.size());
//        System.out.println("BidderOwner random: " + bidderOwners.get(randomInRange(0, bidderOwners.size() - 1)));
//        System.out.println("sonataUsers list size: " + sonataUsers.size());
//        System.out.println("SonataUser random: " + sonataUsers.get(randomInRange(0, sonataUsers.size() - 1)));
//        System.out.println("sonataCurrencies list size: " + sonataCurrencies.size());
//        System.out.println("Currencies: ");
//        sonataCurrencies.entrySet()
//                        .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

        List<MergedResult> mergedResults = crossLists(bidderOwners, sonataUsers);
        System.out.println("Size: " + mergedResults.size());
        mergedResults.forEach(System.out::println);

        List<MergedResult> ownersWithBadCurrencyCountry =
                findBidderOwnersWithBadCurrencyCountry(sonataCurrencies, mergedResults);
        System.out.println("Size: " + ownersWithBadCurrencyCountry.size());
        ownersWithBadCurrencyCountry.forEach(System.out::println);

        List<MergedResult> mergedResultsChanged = crossListsChanged(bidderOwners, sonataUsers);
        System.out.println("Size: " + mergedResultsChanged.size());
        mergedResultsChanged.forEach(System.out::println);

        List<MergedResult> usersWithBadCurrencyCountry =
                findSonataUsersWithBadCurrencyCountry(sonataCurrencies, mergedResultsChanged);
        System.out.println("Size: " + usersWithBadCurrencyCountry.size());
        usersWithBadCurrencyCountry.forEach(System.out::println);
    }

    private static List<MergedResult> findBidderOwnersWithBadCurrencyCountry(Map<String, SonataCurrency> sonataCurrencies,
                                                                             List<MergedResult> mergedResults) {
        System.out.println("");
        System.out.println("----- bidder owners with bad currency-country ----");
        return mergedResults.stream()
                            .filter(mergedResult -> mergedResult.getCountry() != null)
                            .filter(mergedResult -> {
                                SonataCurrency sonataCurrency =
                                        sonataCurrencies.values().stream()
                                                        .filter(currency -> mergedResult
                                                                .getCurrency()
                                                                .equalsIgnoreCase(currency.getCurrency()
                                                                                          .getBaseCurrency()))
                                                        .findFirst().orElse(null);
                                if (sonataCurrency == null) {
                                    throw new IllegalStateException("ERROR con las currencies");
                                }
                                return !mergedResult.getCountry().equals(sonataCurrency.getCountryCode());

                            })
                            .collect(Collectors.toList());
    }

    private static List<MergedResult> findSonataUsersWithBadCurrencyCountry(Map<String, SonataCurrency> sonataCurrencies,
                                                                            List<MergedResult> mergedResults) {
        System.out.println("");
        System.out.println("----- sonata users with bad currency-country ----");
        return mergedResults.stream()
                            .filter(mergedResult -> mergedResult.getCurrency() != null)
                            .filter(mergedResult -> {
                                SonataCurrency sonataCurrency = sonataCurrencies.get(mergedResult.getCountry());
                                if (sonataCurrency == null) {
                                    System.out.println("Country with no currency: " + mergedResult.getCountry());
                                }

                                return sonataCurrency == null ||
                                       !mergedResult.getCurrency().equals(sonataCurrency.getCurrency().getBaseCurrency());

                            })
                            .collect(Collectors.toList());
    }

    private static List<MergedResult> crossLists(List<BidderOwner> bidderOwners, List<SonataUser> sonataUsers) {
        System.out.println("");
        System.out.println("----- bidder owners crossed with sonata users ----");
        return bidderOwners.stream()
                           .map(owner -> sonataUsers.stream()
                                                    .filter(user -> user.get_id().equals(owner.get_id()))
                                                    .findAny()
                                                    .map(user -> new MergedResult(user.get_id(),
                                                                                  owner.getWallet().getCurrency(),
                                                                                  user.getCountry()))
                                                    .orElse(new MergedResult(owner.get_id(),
                                                                             owner.getWallet().getCurrency(),
                                                                             null)))
                           .collect(Collectors.toList());
    }

    private static List<MergedResult> crossListsChanged(List<BidderOwner> bidderOwners, List<SonataUser> sonataUsers) {
        System.out.println("");
        System.out.println("----- sonata users crossed with bidder owners ----");
        return sonataUsers.stream()
                          .map(user -> bidderOwners.stream()
                                                   .filter(owner -> owner.get_id().equals(user.get_id()))
                                                   .findAny()
                                                   .map(owner -> new MergedResult(owner.get_id(),
                                                                                  owner.getWallet().getCurrency(),
                                                                                  user.getCountry()))
                                                   .orElse(new MergedResult(user.get_id(),
                                                                            null,
                                                                            user.getCountry())))
                          .collect(Collectors.toList());
    }


    private static int randomInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
