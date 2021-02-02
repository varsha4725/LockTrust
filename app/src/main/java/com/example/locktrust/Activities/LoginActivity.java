package com.example.locktrust.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.example.locktrust.R;
import com.example.locktrust.Utils.Helper;
import com.example.locktrust.widgets.CustomButton;
import com.example.locktrust.widgets.CustomDialogManager;
import com.example.locktrust.widgets.CustomEditTextMedium;
import com.example.locktrust.widgets.CustomTextViewMedium;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.flImsge)
    FrameLayout flImsge;
    @BindView(R.id.lyOptions)
    LinearLayout lyOptions;
    @BindView(R.id.ivhelloImage)
    ImageView ivhelloImage;
    @BindView(R.id.etUserEmail)
    CustomEditTextMedium etUserEmail;
    @BindView(R.id.tvView)
    CustomTextViewMedium tvView;
    @BindView(R.id.etPassword)
    CustomEditTextMedium etPassword;
    @BindView(R.id.btnLoginSystem)
    CustomButton btnLoginSystem;
    @BindView(R.id.LayoutBase)
    RelativeLayout LayoutBase;
    @BindView(R.id.tvOptionLogin)
    CustomTextViewMedium tvOptionLogin;
    @BindView(R.id.tvOptionSignIn)
    CustomTextViewMedium tvOptionSignIn;
    @BindView(R.id.ScrollLogin)
    NestedScrollView ScrollLogin;
    @BindView(R.id.ScrollSignIn)
    NestedScrollView ScrollSignIn;
    @BindView(R.id.etNameSignup)
    CustomEditTextMedium etNameSignup;
    @BindView(R.id.etmailIdSignUp)
    CustomEditTextMedium etmailIdSignUp;
    @BindView(R.id.etPasswordSignUp)
    CustomEditTextMedium etPasswordSignUp;
    @BindView(R.id.etpasswodAgainSignUp)
    CustomEditTextMedium etpasswodAgainSignUp;
    @BindView(R.id.etMobleSignUp)
    CustomEditTextMedium etMobleSignUp;
    @BindView(R.id.btnSignUp)
    CustomButton btnSignUp;
    @BindView(R.id.tvAlreadyUser)
    CustomTextViewMedium tvAlreadyUser;

    private String strUser = "", strUserFlag = "";
    private Dialog mDialog;
    CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    GoogleSignInClient mGoogleSignInClient;
    private String user_id = "", f_name = "", m_name = "", l_name = "", gender = "",
            profile_image = "", full_name = "", email_id = "", facabookId = "", fb_image_url = "", fb_link = "";
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();//initial function
    }

    private void init() {

        tvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                //  etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

    }


    @OnClick({R.id.btnLoginSystem})
    public void onClick() {
        if (validate()) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        } else {

        }
    }


    private boolean validate() {
        if (TextUtils.isEmpty(etUserEmail.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_email));
            return false;
        } else if (!Helper.isValidMail(etUserEmail.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_valid_email));
            return false;
        } else if (TextUtils.isEmpty(etPassword.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_password));
            return false;
        }
        /*else if (!Helper.isValidPassword(etPassword.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_valid_passord));
            return false;
        }*/
        else
            return true;
    }


    private boolean validateSignUp() {
        if (TextUtils.isEmpty(etmailIdSignUp.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_email));
            return false;
        } else if (!Helper.isValidMail(etNameSignup.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_name));
            return false;
        } else if (!Helper.isValidMail(etmailIdSignUp.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_valid_email));
            return false;
        } else if (TextUtils.isEmpty(etPasswordSignUp.getText().toString()) || etPasswordSignUp.length() < 6) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_password));
            return false;
        } else if (TextUtils.isEmpty(etpasswodAgainSignUp.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_password));
            return false;
        } else if (TextUtils.isEmpty(etMobleSignUp.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_Mobile));
            return false;
        }
        return true;
    }


    @OnClick({R.id.tvOptionLogin, R.id.tvOptionSignIn, R.id.btnSignUp,R.id.tvAlreadyUser})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvOptionLogin:
                ScrollLogin.setVisibility(View.VISIBLE);
                ScrollSignIn.setVisibility(View.GONE);
                tvOptionLogin.setBackground(getDrawable(R.drawable.button_back1));
                tvOptionSignIn.setBackground(getDrawable(R.drawable.button_gradient_background_add_credits));
                break;
            case R.id.tvOptionSignIn:
                tvOptionLogin.setBackground(getDrawable(R.drawable.button_gradient_background_add_credits));
                ScrollLogin.setVisibility(View.GONE);
                ScrollSignIn.setVisibility(View.VISIBLE);
                tvOptionSignIn.setBackground(getDrawable(R.drawable.button_back1));
                break;
            case R.id.btnSignUp:
                if (validateSignUp()) {
                    tvOptionLogin.setBackground(getDrawable(R.drawable.button_gradient_background_add_credits));
                    ScrollLogin.setVisibility(View.GONE);
                    ScrollSignIn.setVisibility(View.VISIBLE);
                    tvOptionSignIn.setBackground(getDrawable(R.drawable.button_back1));
                }
                break;
            case R.id.tvAlreadyUser:
                tvOptionLogin.setBackground(getDrawable(R.drawable.button_gradient_background_add_credits));
                ScrollLogin.setVisibility(View.GONE);
                ScrollSignIn.setVisibility(View.VISIBLE);
                tvOptionSignIn.setBackground(getDrawable(R.drawable.button_back1));
                break;

        }
    }
}
