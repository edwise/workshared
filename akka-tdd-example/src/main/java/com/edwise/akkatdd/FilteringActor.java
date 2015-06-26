package com.edwise.akkatdd;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;

public class FilteringActor extends UntypedActor {

    private ActorRef nextActor;
    private int bufferSize;
    private List<Event> lastMessages;

    public FilteringActor(ActorRef nextActor, int bufferSize) {
        this.nextActor = nextActor;
        this.bufferSize = bufferSize;
        lastMessages = new ArrayList<>(bufferSize);
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Event) {
            Event event = (Event) message;
            if (!lastMessages.contains((event))) {
                lastMessages.add(event);
                nextActor.tell(event, getSelf());
                if (lastMessages.size() > bufferSize) {
                    // TODO borrar el más antiguo...
                }
            }
        }
    }
}
