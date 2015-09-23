package com.edwise.restmultipart;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class Application extends ResourceConfig {

    public Application() {
        packages("com.edwise.restmultipart");

        register(JacksonFeature.class);
        register(MultiPartFeature.class);
    }
}
