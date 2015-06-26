package com.edwise.akkatdd;

import akka.actor.UntypedActor;
import com.edwise.akkatdd.message.GetState;
import com.edwise.akkatdd.message.SilentMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SilentActor extends UntypedActor {

    private List<String> internalState = new ArrayList<>();

    @Override
    public void onReceive(Object message) {
        if (message instanceof SilentMessage) {
            internalState.add(((SilentMessage) message).getData());
        }
        else if (message instanceof GetState) {
            ((GetState) message).getReceiver().tell(this.getState(), getSelf());
        }
    }

    public List<String> getState() {
        return Collections.unmodifiableList(internalState);
    }
}
