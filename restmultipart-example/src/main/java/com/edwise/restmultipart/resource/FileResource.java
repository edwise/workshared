package com.edwise.restmultipart.resource;

import com.edwise.restmultipart.entity.FileInfo;
import com.edwise.restmultipart.entity.PostResult;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("file")
public class FileResource {

    private final static String FILE_PATH = "C:/temp/copies/";

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getAllUsers() {
        List<String> fileNames = new ArrayList<>();
        try (DirectoryStream<java.nio.file.Path> directoryStream =
                     Files.newDirectoryStream(Paths.get(FILE_PATH))) {
            for (java.nio.file.Path path : directoryStream) {
                if (path.toFile().isFile())
                    fileNames.add(path.getFileName().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getFileByName(@PathParam("name") String name) {
        File file = new File(FILE_PATH + name);
        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment; filename=\"" + name + "\"");
        return response.build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(FormDataMultiPart formParams) {
        // TODO tratar campos con Strings...
        PostResult result = new PostResult();
        for (Map.Entry<String, List<FormDataBodyPart>> entryBodyPart : formParams.getFields().entrySet()) {
            for (FormDataBodyPart bodyPart : entryBodyPart.getValue()) {
                String fileLocation = FILE_PATH
                        + bodyPart.getFormDataContentDisposition().getFileName();
                try {
                    Files.copy(bodyPart.getEntityAs(File.class).toPath(),
                            Paths.get(fileLocation),
                            StandardCopyOption.REPLACE_EXISTING);

                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFileName(bodyPart.getFormDataContentDisposition().getFileName());
                    fileInfo.setTempPath(createURIFromResource(
                            bodyPart.getFormDataContentDisposition().getFileName()).toString());
                    result.addPath(fileInfo);
                    result.addOriginalPayloadEntry("a", "123");
                    result.addOriginalPayloadEntry("b", "456");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return Response.status(Response.Status.OK).entity(result).build();
    }

    private URI createURIFromResource(String fileName) {
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        return uriBuilder.path(fileName).build();
    }
}
