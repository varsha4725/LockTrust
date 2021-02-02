package com.example.locktrust.widgets;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.locktrust.R;
import com.example.locktrust.interfaces.DialogListener;
import com.example.locktrust.interfaces.ImageDialogActionListener;


public class CustomDialogManager {

    private static final int ANIM_TIME = 100;


    public static void showYesNoDialog(Context context, String message, final DialogListener dialogListener) {
        if (context != null) {
            AlertDialog.Builder build = new AlertDialog.Builder(context);
            final AlertDialog okCancelDialog;
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_yes_no, null);
            build.setView(view);
            okCancelDialog = build.create();
            okCancelDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            //  final TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            final TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);

            final TextView btnYes = (TextView) view.findViewById(R.id.btnYes);
            btnYes.setText("Yes");
            final TextView btnNo = (TextView) view.findViewById(R.id.btnNo);
            btnNo.setText("Cancel");


            btnYes.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    okCancelDialog.dismiss();
                    if (dialogListener != null)
                        dialogListener.onButtonClicked(Dialog.BUTTON_POSITIVE);
                }
            });

            btnNo.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    okCancelDialog.dismiss();
                    if (dialogListener != null)
                        dialogListener.onButtonClicked(Dialog.BUTTON_NEGATIVE);
                }

            });
            //  tvTitle.setText(R.string.app_name_show);
            if (TextUtils.isEmpty(message))
                tvMessage.setText("");
            else
                tvMessage.setText(message);

            okCancelDialog.show();
        }
    }

    public static void showOkCancelDialog(Context context, String message, final DialogListener dialogListener, final String ref) {
        if (context != null) {
            AlertDialog.Builder build = new AlertDialog.Builder(context);
            final AlertDialog okCancelDialog;
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_ok_cancel, null);
            build.setView(view);
            okCancelDialog = build.create();
            okCancelDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            final TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            final TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
            final CustomButton btnYes = (CustomButton) view.findViewById(R.id.btnYes);
            btnYes.setText("Yes");
            final CustomButton btnNo = (CustomButton) view.findViewById(R.id.btnNo);
            btnNo.setText("No");


            btnYes.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    okCancelDialog.dismiss();
                    if (dialogListener != null)
                        dialogListener.onButtonClicked(Dialog.BUTTON_POSITIVE, ref);
                }
            });

            btnNo.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    okCancelDialog.dismiss();
                    if (dialogListener != null)
                        dialogListener.onButtonClicked(Dialog.BUTTON_NEGATIVE, ref);
                }

            });
            tvTitle.setText(context.getString(R.string.app_name));
            if (TextUtils.isEmpty(message))
                tvMessage.setText("");
            else
                tvMessage.setText(message);

            okCancelDialog.show();
        }
    }


    public static void showSendMailDialog(Context context, String toSend, final DialogListener dialogListener) {
        if (context != null) {
            AlertDialog.Builder build = new AlertDialog.Builder(context);
            final AlertDialog okCancelDialog;
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_send_mail, null);
            build.setView(view);
            okCancelDialog = build.create();
            okCancelDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            final TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            final TextView tvToSend = (TextView) view.findViewById(R.id.tvToSend);
            final EditText etMessage = (EditText) view.findViewById(R.id.etMessage);
           // final TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
            final CustomButton btnYes = (CustomButton) view.findViewById(R.id.btnYes);
            btnYes.setText("Yes");
            final CustomButton btnNo = (CustomButton) view.findViewById(R.id.btnNo);
            btnNo.setText("No");
            tvToSend.setText("To : "+toSend);

            btnYes.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!etMessage.getText().toString().equals(""))
                    {
                        okCancelDialog.dismiss();
                        if (dialogListener != null)
                            dialogListener.onButtonClicked(Dialog.BUTTON_POSITIVE,etMessage.getText().toString());
                    }else
                    {
                        Toast.makeText(context, "Please enter send mail message.", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            btnNo.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    okCancelDialog.dismiss();
                    if (dialogListener != null)
                        dialogListener.onButtonClicked(Dialog.BUTTON_NEGATIVE);
                }

            });
           // tvTitle.setText(context.getString(R.string.app_name));
           /* if (TextUtils.isEmpty(message))
                tvMessage.setText("");
            else
                tvMessage.setText(message);*/

            okCancelDialog.show();
        }
    }





    public static void showOkDialog(final Context context, String message) {
        if (context != null) {
            AlertDialog.Builder build = new AlertDialog.Builder(context);
            final AlertDialog okCancelDialog;
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_ok, null);
            build.setView(view);
            okCancelDialog = build.create();
            okCancelDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            final TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            final TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
            final TextView btnYes = (TextView) view.findViewById(R.id.btnYes);
            btnYes.setText("OK");
            btnYes.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    okCancelDialog.dismiss();
                }
            });

            tvTitle.setText(R.string.app_name);

            if (TextUtils.isEmpty(message))
                tvMessage.setText("");
            else
                tvMessage.setText(message);

            okCancelDialog.show();
            okCancelDialog.setCancelable(false);
            okCancelDialog.setCanceledOnTouchOutside(false);
        }
    }

    public static void showOkLoginDialog(final Context context,DialogListener dialogListener) {
        if (context != null) {
            AlertDialog.Builder build = new AlertDialog.Builder(context);
            final AlertDialog okCancelDialog;
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_ok, null);
            build.setView(view);
            okCancelDialog = build.create();
            okCancelDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            final TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            final TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
            final TextView btnYes = (TextView) view.findViewById(R.id.btnYes);
            btnYes.setText("OK");
            btnYes.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    okCancelDialog.dismiss();
                    if (dialogListener != null)
                        dialogListener.onButtonClicked(Dialog.BUTTON_POSITIVE);
                }
            });

            tvTitle.setText(R.string.app_name);


           tvMessage.setText(R.string.login_again);

            okCancelDialog.show();
            okCancelDialog.setCancelable(false);
            okCancelDialog.setCanceledOnTouchOutside(false);
        }
    }

//    public static void showTermsConditionDialog(final Context context) {
//        if (context != null) {
//            AlertDialog.Builder build = new AlertDialog.Builder(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
//            final AlertDialog okCancelDialog;
//            View view = LayoutInflater.from(context).inflate(R.layout.dialog_webview, null);
//            build.setView(view);
//            okCancelDialog = build.create();
//            okCancelDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//            final TextView btnYes = (TextView) view.findViewById(R.id.btnYes);
//            final WebView wvTermsCondition = (WebView) view.findViewById(R.id.wvTermsCondition);
//            btnYes.setText("OK");
//            startWebView(context,wvTermsCondition,"http://idevint.com/we-are-durban/terms-and-conditions");
//
//            btnYes.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    okCancelDialog.dismiss();
//                }
//            });
//
//
//
//            okCancelDialog.show();
//            okCancelDialog.setCanceledOnTouchOutside(false);
//        }
//    }


    private static void startWebView(final Context context, WebView webView, String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        webView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                   return true;
            }

            //Show loader on url load
            public void onLoadResource(WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    //Log.e("PaymentUrl ;",url);

                    progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }

            public void onPageFinished(WebView view, String url) {
                try {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();


                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        });

        // Javascript inabled on webview
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);


    }

    //Dialog for single ok Button
    public static void showOkDialog(Context context, CharSequence message, final DialogListener dialogListener) {
        if (context != null) {
            AlertDialog.Builder build = new AlertDialog.Builder(context);
            final AlertDialog okCancelDialog;
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_ok, null);
            build.setView(view);
            okCancelDialog = build.create();
            okCancelDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            final TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            final TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);

            final TextView btnYes = (TextView) view.findViewById(R.id.btnYes);
            btnYes.setText("Ok");

            btnYes.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    okCancelDialog.dismiss();
                    if (dialogListener != null)
                        dialogListener.onButtonClicked(Dialog.BUTTON_POSITIVE);

                }
            });


            tvTitle.setText(R.string.app_name);
            if (TextUtils.isEmpty(message))
                tvMessage.setText("");
            else
                tvMessage.setText(message);

            okCancelDialog.show();
            okCancelDialog.setCanceledOnTouchOutside(false);
        }
    }
    //Dialog for Transaction failed
    public static void showTransactionFailedDialog(Context context,final DialogListener dialogListener) {
        if (context != null) {
            AlertDialog.Builder build = new AlertDialog.Builder(context);
            final AlertDialog okCancelDialog;
            View view = LayoutInflater.from(context).inflate(R.layout.layout_payment_fail, null);
            build.setView(view);
            okCancelDialog = build.create();
            okCancelDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            final Button btnRetry =  view.findViewById(R.id.btnRetry);

            btnRetry.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    okCancelDialog.dismiss();
                    if (dialogListener != null)
                        dialogListener.onButtonClicked(Dialog.BUTTON_POSITIVE);

                }
            });

            okCancelDialog.show();
            okCancelDialog.setCanceledOnTouchOutside(false);
        }
    }
    //Dialog for single ok for login
/*
    public static void showLogoutOkDialog(Context context, CharSequence message, LogoutListener logoutListener) {
        if (context != null) {
            AlertDialog.Builder build = new AlertDialog.Builder(context);
            final AlertDialog okCancelDialog;
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_ok, null);
            build.setView(view);
            okCancelDialog = build.create();
            okCancelDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            final TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            final TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);

            final TextView btnYes = (TextView) view.findViewById(R.id.btnYes);
            btnYes.setText("Ok");

            btnYes.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    okCancelDialog.dismiss();
                    if (logoutListener != null)
                        logoutListener.onLogOutClicked(Dialog.BUTTON_POSITIVE);

                }
            });

            tvTitle.setText(R.string.app_name_show);
            if (TextUtils.isEmpty(message))
                tvMessage.setText("");
            else
                tvMessage.setText(message);

            okCancelDialog.show();
            okCancelDialog.setCanceledOnTouchOutside(false);
        }
    }
*/

   public static Dialog showProgressDialog(Context context) {
        if (context == null) {
            return null;
        }
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.vw_custom_progress_bar);
        //web view use for load image
        WebView webView = (WebView) dialog.findViewById(R.id.wvLoad);
        webView.setBackgroundColor(Color.TRANSPARENT); //for gif without background
        webView.loadUrl("file:///android_asset/html/loader.html");


        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        if (!((Activity) context).isFinishing()) {
            dialog.show();
            //show dialog
        }

        return dialog;

    }

//    public static void showAddOtherDialog(final Context context, String message, String otherValue, String identity, final OnInviteAddClickListener dialogListener) {
//        final AlertDialog.Builder build = new AlertDialog.Builder(context);
//        final AlertDialog okCancelDialog;
//        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_other, null);
//        build.setView(view);
//        okCancelDialog = build.create();
//        okCancelDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        okCancelDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//
//        final TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
//        SpannableString spanString = new SpannableString(message);
//        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
//        tvTitle.setText(spanString);
//
//        final EditText etContactValue = (EditText) view.findViewById(R.id.etContactValue);
//        if (!otherValue.equals("")) {
//            etContactValue.setText(otherValue);
//        }
//        etContactValue.setHint("Other");
//
//        final TextView btnSave = (TextView) view.findViewById(R.id.btnSave);
//        final TextView btnCancel = (TextView) view.findViewById(R.id.btnCancel);
//
//        btnSave.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!etContactValue.getText().toString().trim().isEmpty()) {
//                    okCancelDialog.dismiss();
//
//                    if (dialogListener != null) {
//
//                        dialogListener.onAddClicked(Dialog.BUTTON_POSITIVE, etContactValue.getText().toString().trim(),identity);
//
//                    }
//                } else {
//                    CustomDialogManager.showOkDialog(context, "Please do_not_move the other value.");
//                }
//
//            }
//        });
//
//        btnCancel.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                okCancelDialog.dismiss();
//
//                if (dialogListener != null)
//                    dialogListener.onAddClicked(Dialog.BUTTON_NEGATIVE, "",identity);
//            }
//
//        });
//
//        okCancelDialog.show();
//    }


    public static void showShortToast(CharSequence msg, Context ctx) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }



    //Method to upload image
    public static void showImagePickDialog(final Context context, String title, final ImageDialogActionListener listener) {
        if(context != null)
        {
            final Dialog dialog = new Dialog(context, R.style.CustomDialog);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_photo_option, null);
            dialog.setContentView(view);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(lp);

            final TextView tvCamera = (TextView) view.findViewById(R.id.tvCamera);
            final TextView tvGallery = (TextView) view.findViewById(R.id.tvGallery);
            final ImageView ivCamera = (ImageView) view.findViewById(R.id.ivCamera);
            final ImageView ivGallery = (ImageView) view.findViewById(R.id.ivGallery);
            final TextView tvUploadImage = (TextView) view.findViewById(R.id.tvUploadImage);

            tvUploadImage.setText(title);

            //Adding animation effects to the view
            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.animation_rotate);
            Animation zoom_in = AnimationUtils.loadAnimation(context, R.anim.animation_zoom_in);
            Animation zoom_animation = AnimationUtils.loadAnimation(context, R.anim.animation_zoom_in);

            tvUploadImage.startAnimation(zoom_animation);
            tvCamera.startAnimation(zoom_in);
            tvGallery.startAnimation(zoom_in);

            ivCamera.startAnimation(hyperspaceJumpAnimation);
            ivGallery.startAnimation(hyperspaceJumpAnimation);

            ivCamera.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    dialog.dismiss();
                    if (listener != null)
                        listener.onCameraOptionClicked();
                }
            });

            ivGallery.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    dialog.dismiss();
                    if (listener != null)
                        listener.onGalleryOptionClicked();
                }
            });

            dialog.setCancelable(true);
            dialog.show();
        }
    }
}
