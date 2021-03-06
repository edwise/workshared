package com.edwise.restclientmultipart;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

public class PostClient {

    public static void main(String[] args) {
        final FileDataBodyPart filePart =
                new FileDataBodyPart("file", new File("C:/P3220236.jpg"));
        final FileDataBodyPart secondFilePart =
                new FileDataBodyPart("file", new File("C:/eula.1042.txt"));
        final FormDataMultiPart multipart = (FormDataMultiPart) new FormDataMultiPart()
//                .field("dato123", "valor456")
//                .field("dato567", "valor789")
                .bodyPart(filePart)
                .bodyPart(secondFilePart);

        Client client =
                ClientBuilder.newClient(
                        new ClientConfig()
                                .register(JacksonJsonProvider.class)
                                .register(MultiPartFeature.class));
        WebTarget target = client.target("http://localhost:8080/api/").path("file");

        final Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(multipart, multipart.getMediaType()));

        System.out.println("Response Status: " + response.getStatus());
        System.out.println("Body: " + response.readEntity(String.class));
    }
}
