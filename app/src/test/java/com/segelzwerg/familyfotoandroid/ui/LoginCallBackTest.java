package com.segelzwerg.familyfotoandroid.ui;

import android.content.Context;
import android.content.Intent;

import com.segelzwerg.familyfotoandroid.familyfotoservice.AuthToken;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoginCallBackTest {


    private LoginCallBack<AuthToken> loginCallBack;
    private Context context;

    @BeforeEach
    public void setUp() {
        context = mock(Context.class);
        loginCallBack = new LoginCallBack<>(context);
    }

    @Test
    public void onResponse() {
        Call<AuthToken> call = (Call<AuthToken>) mock(Call.class);
        AuthToken token = new AuthToken("token");
        Response<AuthToken> response = Response.success(token);
        loginCallBack.onResponse(call, response);
        verify(context, times(1))
                .startActivity(any(Intent.class), eq(null));
    }

    @Test
    public void onFailure() {
    }
}