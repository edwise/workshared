package com.edwise.akkatdd;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import akka.testkit.TestActorRef;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import scala.concurrent.duration.Duration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FilteringActorTest {

    private static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        system.shutdown();
        system.awaitTermination(Duration.create("10 seconds"));
    }

    @Test
    public void mustFilterOutParticularMessages() {
        new JavaTestKit(system) {{
            TestActorRef<FilteringActor> filterActor =
                    TestActorRef.create(system,
                            Props.create(FilteringActor.class,
                                    () -> new FilteringActor(getTestActor(), 5)), "f1");

            filterActor.tell(new Event(1), getTestActor());
            filterActor.tell(new Event(2), getTestActor());
            filterActor.tell(new Event(1), getTestActor());
            filterActor.tell(new Event(3), getTestActor());
            filterActor.tell(new Event(1), getTestActor());
            filterActor.tell(new Event(4), getTestActor());
            filterActor.tell(new Event(5), getTestActor());
            filterActor.tell(new Event(5), getTestActor());
            filterActor.tell(new Event(6), getTestActor());

            Object[] msgs = receiveN(9);
            List<Integer> values =
                    Arrays.stream(msgs)
                            .map((e) -> ((Event) e).getNum())
                            .collect(Collectors.toList());
            assertThat(values).contains(1, 2, 3, 4, 5, 6);
        }};
    }

    @Test
    public void mustNotSendMsgWhenValueExists() {
        new JavaTestKit(system) {{
            TestActorRef<FilteringActor> filterActor =
                    TestActorRef.create(system,
                            Props.create(FilteringActor.class,
                                    () -> new FilteringActor(getTestActor(), 5)), "f2");

            filterActor.tell(new Event(1), getTestActor());
            expectMsgClass(Event.class);

            filterActor.tell(new Event(1), getTestActor());
            expectNoMsg();
        }};
    }
}