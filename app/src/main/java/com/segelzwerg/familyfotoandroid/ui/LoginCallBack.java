package com.segelzwerg.familyfotoandroid.ui;

import android.content.Context;
import android.content.Intent;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class LoginCallBack<AuthToken> implements Callback<AuthToken> {
    private final Context context;

    public LoginCallBack(Context context) {
        this.context = context;
    }

    /**
     * Redirects to MainActivity.
     * {@inheritDoc}
     */
    @Override
    public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent, null);
    }

    /**
     * {@inheritDoc}
     **/
    @SneakyThrows
    @Override
    public void onFailure(Call<AuthToken> call, Throwable t) {
        throw t;
    }

}
