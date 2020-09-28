package com.segelzwerg.familyfotoandroid;

import com.segelzwerg.familyfotoandroid.familiyfotoservice.AuthToken;
import com.segelzwerg.familyfotoandroid.familiyfotoservice.FamilyFotoServerService;
import com.segelzwerg.familyfotoandroid.familiyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.familiyfotoservice.utils.RetrofitClientUtil;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Call;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;


public class TestFamilyFotoServerService {
    private FamilyFotoServerService familyFotoServerService;
    private MockWebServer mockWebServer = new MockWebServer();

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer.start();
        familyFotoServerService = RetrofitClientUtil.buildRetrofitInstance(mockWebServer.url("/"))
                .create(FamilyFotoServerService.class);
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testTokenRequest() throws InterruptedException, IOException {
        MockResponse response = new MockResponse()
                .setResponseCode(HttpsURLConnection.HTTP_OK)
                .setBody("{token:token}");
        mockWebServer.enqueue(response);
        LoginCredentials credentials = new LoginCredentials("Marcel", "1234");
        Call<AuthToken> login = familyFotoServerService.login(credentials);
        Response<AuthToken> tokenResponse = login.execute();
        RecordedRequest request = mockWebServer.takeRequest(1, TimeUnit.SECONDS);

        AuthToken expected_token = new AuthToken("token");

        assertThat(request.getPath()).isEqualTo("/api/token");
        assertThat(tokenResponse.code()).isEqualTo(HttpsURLConnection.HTTP_OK);
        assertThat(tokenResponse.body()).usingRecursiveComparison().isEqualTo(expected_token);
    }
}