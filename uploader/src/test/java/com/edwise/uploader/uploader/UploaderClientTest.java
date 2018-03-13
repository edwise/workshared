package com.edwise.uploader.uploader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Eduardo Antón <eduardo.anton@taptapnetworks.com> on 13/03/2018.
 */
public class UploaderClientTest {
    private static final String SERVER = "http://localhost:8080/upload";
    private static final String IMAGEFILENAME = "image.png";

    private CloseableHttpClient client;
    private HttpPut put;
    private CloseableHttpResponse response;

    @Before
    public final void before() {
        client = HttpClientBuilder.create().build();
        put = new HttpPut(SERVER);
    }

    @Test
    public void callToUpload() throws IOException {
        final URL localFileUrl = Thread.currentThread()
                              .getContextClassLoader()
                              .getResource("uploads/" + IMAGEFILENAME);

        final File file = new File(localFileUrl.getPath());
        final FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);

        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addPart("file", fileBody);
        final HttpEntity entity = builder.build();

        put.setEntity(entity);
        put.setHeader(new BasicHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjU0NTg4OTJjZGJmYzg4OTAwMzAwMDAwYiIsImlhdCI6MTUyMDg2NjEzMSwiZXhwIjoxNTIwOTUyNTMxfQ.W8tPfPIUVnlfyMmFvNiY6kLAb9rA2xtz-1PMFw05poKwwbo6flcgzOC22mCfpLMhGpBWTPagp0gwxPBE02Jtkw"));
        response = client.execute(put);

        final int statusCode = response.getStatusLine().getStatusCode();
        final String responseString = getContent();
        final String contentTypeInHeader = getContentTypeHeader();
        assertThat(statusCode, equalTo(HttpStatus.SC_OK));
        assertTrue(contentTypeInHeader.contains("Content-Type: multipart/form-data;"));
        System.out.println(responseString);
        System.out.println("PUT Content Type: " + contentTypeInHeader);
    }

    private String getContent() throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String body = "";
        StringBuilder content = new StringBuilder();
        while ((body = rd.readLine()) != null) {
            content.append(body).append("\n");
        }
        return content.toString().trim();
    }

    private String getContentTypeHeader() {
        return put.getEntity()
                  .getContentType()
                  .toString();
    }
}
