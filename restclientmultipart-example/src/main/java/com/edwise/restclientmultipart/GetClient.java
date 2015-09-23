package com.edwise.restclientmultipart;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class GetClient {

    public static void main(String[] args) {
        Client client =
                ClientBuilder.newClient(
                        new ClientConfig()
                                .register(JacksonJsonProvider.class)
                                .register(MultiPartFeature.class));

        WebTarget target = client.target("http://localhost:8080/api/").path("file");
        Response response = target.path("P3220236.jpg").request().get();

        System.out.println("Response Status: " + response.getStatus());

        try {
            Files.copy(
                    response.readEntity(File.class).toPath(),
                    Paths.get("C:/temp/downloaded/downloadedP3220236.jpg"),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
