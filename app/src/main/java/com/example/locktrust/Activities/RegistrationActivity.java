package com.example.locktrust.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.locktrust.R;
import com.example.locktrust.Utils.Helper;
import com.example.locktrust.interfaces.DialogListener;
import com.example.locktrust.network.API;
import com.example.locktrust.network.RetrofitClient;
import com.example.locktrust.widgets.CustomButton;
import com.example.locktrust.widgets.CustomCheckBox;
import com.example.locktrust.widgets.CustomDialogManager;
import com.example.locktrust.widgets.CustomEditTextMedium;
import com.example.locktrust.widgets.CustomTextViewMedium;
import com.example.locktrust.widgets.CustomTextViewMedium;
import com.example.locktrust.widgets.CustomTextViewMedium;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.etFullName)
    CustomEditTextMedium etFullName;
    @BindView(R.id.etEmailId)
    CustomEditTextMedium etEmailId;
    @BindView(R.id.etPhoneNumber)
    CustomEditTextMedium etPhoneNumber;
    @BindView(R.id.etPassword)
    CustomEditTextMedium etPassword;
    @BindView(R.id.etCity)
    CustomEditTextMedium etCity;
    @BindView(R.id.cbEmailDiscount)
    CustomCheckBox cbEmailDiscount;
    @BindView(R.id.cbTermsCondition)
    CustomCheckBox cbTermsCondition;
    @BindView(R.id.cbPrivacyPolicy)
    CustomCheckBox cbPrivacyPolicy;
    @BindView(R.id.btnLogin)
    CustomButton btnLogin;
    @BindView(R.id.tvSignUp)
    CustomTextViewMedium tvSignUp;
    @BindView(R.id.tvTermsConditionSignup)
    CustomTextViewMedium tvTermsConditionSignup;
    @BindView(R.id.tvPrivacyPolicySignup)
    CustomTextViewMedium tvPrivacyPolicySignup;
    private Dialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        init();//initial function
    }


    //initial function call
    private void init() {

    }

    //validation function for the form
    private boolean validate() {
        if (TextUtils.isEmpty(etFullName.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_name));
            return false;
        } else if (TextUtils.isEmpty(etEmailId.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_email));
            return false;
        } else if (!Helper.isValidMail(etEmailId.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_valid_email));
            return false;
        } else if (TextUtils.isEmpty(etPhoneNumber.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_mobile_no));
            return false;
        } else if (etPhoneNumber.getText().toString().length() < 10) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_valid_mobile));
            return false;
        } else if (TextUtils.isEmpty(etPassword.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_password));
            return false;
        } else if (TextUtils.isEmpty(etCity.getText().toString())) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_enter_city));
            return false;
        } else if (!cbTermsCondition.isChecked()) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_accept_terms_conditions));
            return false;
        } else if (!cbPrivacyPolicy.isChecked()) {
            CustomDialogManager.showOkDialog(this, getString(R.string.please_accept_policy));
            return false;
        }
        return true;
    }

    @OnClick({R.id.btnLogin, R.id.tvSignUp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (validate()) {
                    if (Helper.isNetworkAvailble(com.example.locktrust.Activities.RegistrationActivity.this)) {

                    }
                }

                break;
            case R.id.tvSignUp:
                finish();
                break;
        }
    }

    //This function is used for register user


    @OnClick({R.id.tvTermsConditionSignup, R.id.tvPrivacyPolicySignup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvTermsConditionSignup:
      //     startActivity(new Intent(com.example.locktrust.Activities.RegistrationActivity.this,Terms_condition_signup_Ativity.class));

                break;
            case R.id.tvPrivacyPolicySignup:

             //  startActivity(new Intent(com.example.locktrust.Activities.RegistrationActivity.this,Privacy_Policy_signup_Activity.class));
                break;
        }
    }
}
