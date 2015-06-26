package com.edwise.akkatdd.message;

import akka.actor.ActorRef;

public class GetState {

    private ActorRef receiver;

    public GetState(ActorRef receiver) {
        this.receiver = receiver;
    }

    public ActorRef getReceiver() {
        return receiver;
    }
}
