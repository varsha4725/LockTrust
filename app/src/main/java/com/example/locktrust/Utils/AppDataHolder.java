package com.example.locktrust.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.locktrust.R;


/**
 * Created by Prashant.Mali on 01/11/2018.
 */

public class AppDataHolder {
    private static AppDataHolder session;
    private static SharedPreferences prefs;
    public static Context mContext;


    public static AppDataHolder getSession(Context ctx) {
        if (session == null) {
            session = new AppDataHolder();
            prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
            mContext = ctx;
        }
        return session;
    }

    // deviceID means its tocken which gets from firebase
    public String getAccessToken() {
        return prefs.getString(mContext.getString(R.string.access_token), "");
    }

    public void setAccessToken(String accessToken) {
        prefs.edit().putString(mContext.getString(R.string.access_token), accessToken).apply();
    }
    //Access tocken or not
    public void setIsFirstTimeOpenApp(boolean isFirstTimeOpenApp) {
        prefs.edit().putBoolean(mContext.getString(R.string.is_first_time_open_app), isFirstTimeOpenApp).apply();
    }
    public boolean getIsFirstTimeOpenApp() {
        return prefs.getBoolean(mContext.getString(R.string.is_first_time_open_app), true);
    }
    //User Type
    public void setUserType(String userType) {
        prefs.edit().putString(mContext.getString(R.string.user_type), userType).apply();
    }
    public String getUserType() {
        return prefs.getString(mContext.getString(R.string.user_type), "");
    }
    // user id save
    public String getUserId() {
        return prefs.getString(mContext.getString(R.string.user_id), "");
    }

    public void setUserId(String userId) {
        prefs.edit().putString(mContext.getString(R.string.user_id), userId).apply();
    }
    // user name save
    public String getUserName() {
        return prefs.getString(mContext.getString(R.string.user_name), "");
    }

    public void setUserName(String userName) {
        prefs.edit().putString(mContext.getString(R.string.user_name), userName).apply();
    }
    // user contact save
    public String getUserContact() {
        return prefs.getString(mContext.getString(R.string.user_contact), "");
    }

    public void setUserContact(String userContact) {
        prefs.edit().putString(mContext.getString(R.string.user_contact), userContact).apply();
    }
    // user email save
    public String getUserEmail() {
        return prefs.getString(mContext.getString(R.string.user_email), "");
    }

    public void setUserEmail(String userEmail) {
        prefs.edit().putString(mContext.getString(R.string.user_email), userEmail).apply();
    }
    // user profile image
    public String getUserImage() {
        return prefs.getString(mContext.getString(R.string.user_image), "");
    }

    public void setUserImage(String userImage) {
        prefs.edit().putString(mContext.getString(R.string.user_image), userImage).apply();
    }
    // user Country
    public String getUserCountry() {
        return prefs.getString(mContext.getString(R.string.user_country), "");
    }

    public void setUserCountry(String userCountry) {
        prefs.edit().putString(mContext.getString(R.string.user_country), userCountry).apply();
    }
    // user State
    public String getUserState() {
        return prefs.getString(mContext.getString(R.string.user_state), "");
    }

    public void setUserState(String userState) {
        prefs.edit().putString(mContext.getString(R.string.user_state), userState).apply();
    }
    // user City
    public String getUserCity() {
        return prefs.getString(mContext.getString(R.string.user_city), "");
    }

    public void setUserCity(String userCity) {
        prefs.edit().putString(mContext.getString(R.string.user_city), userCity).apply();
    }
    // user address
    public String getUserAddress() {
        return prefs.getString(mContext.getString(R.string.user_address), "");
    }

    public void setUserAddress(String userAddress) {
        prefs.edit().putString(mContext.getString(R.string.user_address), userAddress).apply();
    }
    // user DOB
    public String getUserDOB() {
        return prefs.getString(mContext.getString(R.string.user_dob), "");
    }

    public void setUserDOB(String userDob) {
        prefs.edit().putString(mContext.getString(R.string.user_dob), userDob).apply();
    }
    // user Gender
    public String getUserGender() {
        return prefs.getString(mContext.getString(R.string.user_gender), "");
    }

    public void setUserGender(String userGender) {
        prefs.edit().putString(mContext.getString(R.string.user_gender), userGender).apply();
    }

    // user Credit count
    public String getUserCredits() {
        return prefs.getString(mContext.getString(R.string.user_credits), "");
    }

    public void setUserCredits(String userCredits) {
        prefs.edit().putString(mContext.getString(R.string.user_credits), userCredits).apply();
    }
}
