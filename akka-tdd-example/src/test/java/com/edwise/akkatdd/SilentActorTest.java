package com.edwise.akkatdd;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import akka.testkit.TestActorRef;
import com.edwise.akkatdd.message.GetState;
import com.edwise.akkatdd.message.SilentMessage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import scala.concurrent.duration.Duration;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SilentActorTest {

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
    public void mustChangeState_WhenItReceivesAMessage_SingleThreaded() {
        TestActorRef<SilentActor> silentActor =
                TestActorRef.create(system, Props.create(SilentActor.class), "s1");

        silentActor.tell(new SilentMessage("whisper"), ActorRef.noSender());

        assertThat(silentActor.underlyingActor().getState()).contains("whisper");
    }

    @Test
    public void mustChangeState_WhenItReceivesAMessage_MultiThreaded() {
        new JavaTestKit(system) {{
            TestActorRef<SilentActor> silentActor =
                    TestActorRef.create(system, Props.create(SilentActor.class), "s3");

            silentActor.tell(new SilentMessage("whisper1"), getTestActor());
            silentActor.tell(new SilentMessage("whisper2"), getTestActor());
            silentActor.tell(new GetState(getTestActor()), getTestActor());

            expectMsgEquals(Arrays.asList("whisper1", "whisper2"));
        }};
    }
}