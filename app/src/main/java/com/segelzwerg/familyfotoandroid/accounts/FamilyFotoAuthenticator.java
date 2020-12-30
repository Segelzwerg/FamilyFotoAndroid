package com.segelzwerg.familyfotoandroid.accounts;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.segelzwerg.familyfotoandroid.ui.activities.LoginActivity;

/**
 * Authenticates request to family foto server.
 */
public class FamilyFotoAuthenticator extends AbstractAccountAuthenticator {
    /**
     * The context where the authenticator is initialized.
     */
    private final transient Context mContext;

    /**
     * Constructor.
     * @param context current application context.
     */
    public FamilyFotoAuthenticator(Context context) {
        super(context);
        mContext = context;
    }

    /**
     * Not implemented.
     * @param response from the server.
     * @param accountType name of the account type
     * @return {@link Bundle}
     */
    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    /**
     * Adds an account to the account manager.
     * @param response from the server
     * @param accountType name of the account type
     * @param authTokenType name of the auth token type
     * @param requiredFeatures extra features required
     * @param options {@link Bundle}
     * @return {@link Bundle}
     */
    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response,
                             String accountType,
                             String authTokenType,
                             String[] requiredFeatures,
                             Bundle options) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

        Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    /**
     * Not implemented.
     * @param response from family foto service
     * @param account the account that needs confirmation
     * @param options {@link Bundle}
     * @return {@link Bundle}
     */
    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response,
                                     Account account,
                                     Bundle options) {
        return null;
    }

    /**
     * Not implemented.
     * @param response from the server
     * @param account whichs needs the token
     * @param authTokenType name of the token type
     * @param options {@link Bundle}
     * @return {@link Bundle}
     */
    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response,
                               Account account,
                               String authTokenType,
                               Bundle options) {
        return null;
    }

    /**
     * Not implemented.
     * @param authTokenType name of the token type
     * @return the token string
     */
    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    /**
     * Not implemented.
     * @param response from the server
     * @param account whichs credentials are updated
     * @param authTokenType name of the token type
     * @param options {@link Bundle}
     * @return {@link Bundle}
     */
    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response,
                                    Account account,
                                    String authTokenType,
                                    Bundle options) {
        return null;
    }

    /**
     * Not implemented.
     * @param response from the server
     * @param account to check for feature
     * @param features name of the features
     * @return {@link Bundle}
     */
    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response,
                              Account account,
                              String[] features) {
        return null;
    }
}
