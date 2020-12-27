package com.segelzwerg.familyfotoandroid.familyfotoservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import okhttp3.RequestBody;
import retrofit2.Call;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FamilyFotoUploaderTest {
    public static final String PATH = "/test";

    private FamilyFotoUploader uploader;
    private FamilyFotoServerService server;
    private Header successHeader;
    private Header failedHeader;

    @BeforeEach
    public void setUp() throws IOException {
        server = mock(FamilyFotoServerService.class);
        uploader = new FamilyFotoUploader(server);
        Call<Response> success = mock(Call.class);
        Call<Response> failure = mock(Call.class);
        when(failure.execute()).thenThrow(IOException.class);
        successHeader = new Header();
        successHeader.addToken(1, "abc");
        failedHeader = new Header();
        when(server.upload(eq(successHeader.getHeaders()), any(RequestBody.class)))
                .thenReturn(success);
        when(server.upload(eq(failedHeader.getHeaders()), any(RequestBody.class)))
                .thenReturn(failure);

    }

    @Test
    public void testSuccessfulUpload() {
        assertTrue(uploader.upload(PATH, successHeader));
    }

    @Test
    public void testFailedUpload() {
        assertFalse(uploader.upload(PATH, failedHeader));
    }
}