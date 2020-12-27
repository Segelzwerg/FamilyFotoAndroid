package com.segelzwerg.familyfotoandroid.familyfotoservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import retrofit2.Call;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FamilyFotoUploaderTest {
    public static final String PATH = "/test";

    private FamilyFotoUploader uploader;
    private Header header;
    private FamilyFotoServerService server;

    @BeforeEach
    public void setUp() {
        header = new Header();
        server = mock(FamilyFotoServerService.class);
        uploader = new FamilyFotoUploader(server);
    }

    @Test
    public void testSuccessfulUpload() {
        Call<Response> call = mock(Call.class);
        when(server.upload(any(), any())).thenReturn(call);
        assertTrue(uploader.upload(PATH, header));
    }

    @Test
    public void testFailedUpload() throws IOException {
        Call<Response> call = mock(Call.class);
        when(call.execute()).thenThrow(IOException.class);
        when(server.upload(any(), any())).thenReturn(call);
        assertFalse(uploader.upload(PATH, header));
    }
}