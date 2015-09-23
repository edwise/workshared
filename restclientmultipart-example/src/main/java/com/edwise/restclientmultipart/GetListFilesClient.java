package com.edwise.restclientmultipart;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;

public class GetListFilesClient {

    public static void main(String[] args) {
        Client client =
                ClientBuilder.newClient(
                        new ClientConfig()
                                .register(JacksonJsonProvider.class));

        WebTarget target = client.target("http://localhost:8080/api/").path("file");
        Response response = target
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        System.out.println();
        System.out.println("GET All StatusCode = " + response.getStatus());
        System.out.println("GET Body (object list): ");
        Arrays.asList(response.readEntity(String[].class))
                .forEach(fileName -> System.out.println("--> " + fileName));
    }
}
