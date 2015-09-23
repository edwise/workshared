package com.edwise.restmultipart.resource;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Path("file")
public class FileResource {

    private final static String FILE_PATH = "C:/temp/copies/";

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
    @Produces("text/plain")
    public Response getFileByName(@PathParam("name") String name) {
        File file = new File(FILE_PATH + name);
        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition", "attachment; filename=\"" + name + "\"");
        return response.build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
                               @FormDataParam("file") FormDataContentDisposition fileDetail) {
        String fileLocation = FILE_PATH
                + new Random().nextInt() + "_" + fileDetail.getFileName();
        try {
            Files.copy(uploadedInputStream, Paths.get(fileLocation),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

//      CON JAVA 6
//        try {
//            FileOutputStream out;
//            int read = 0;
//            byte[] bytes = new byte[1024];
//            out = new FileOutputStream(new File(fileLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String output = "File successfully uploaded to : " + fileLocation;
        return Response.status(Response.Status.NO_CONTENT).entity(output).build();
    }

}
