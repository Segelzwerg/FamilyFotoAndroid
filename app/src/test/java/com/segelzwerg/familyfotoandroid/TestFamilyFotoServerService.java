package com.segelzwerg.familyfotoandroid;

import com.google.gson.Gson;
import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthToken;
import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthTokenResponse;
import com.segelzwerg.familyfotoandroid.familyfotoservice.FamilyFotoServerService;
import com.segelzwerg.familyfotoandroid.familyfotoservice.Header;
import com.segelzwerg.familyfotoandroid.familyfotoservice.LoginCredentials;
import com.segelzwerg.familyfotoandroid.familyfotoservice.utils.RetrofitClientUtil;

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
        AuthTokenResponse authTokenResponse = new AuthTokenResponse(new AuthToken("token"), 1);

        Gson gson = new Gson();
        MockResponse response = new MockResponse()
                .setResponseCode(HttpsURLConnection.HTTP_OK)
                .setBody(gson.toJson(authTokenResponse));
        mockWebServer.enqueue(response);
        LoginCredentials credentials = new LoginCredentials("Marcel", "1234");
        Header header = new Header();
        header.addAuthentication(credentials);
        header.addAuthentication(credentials);
        Call<AuthTokenResponse> login = familyFotoServerService.login(header.getHeaders());
        Response<AuthTokenResponse> tokenResponse = login.execute();
        RecordedRequest request = mockWebServer.takeRequest(1, TimeUnit.SECONDS);

        assertThat(request.getPath()).isEqualTo("/api/token");
        assertThat(request.getHeader("Authorization")).isEqualTo("Basic " + credentials.encode());
        assertThat(tokenResponse.code()).isEqualTo(HttpsURLConnection.HTTP_OK);
        assertThat(tokenResponse.body()).usingRecursiveComparison().isEqualTo(authTokenResponse);
    }
}
