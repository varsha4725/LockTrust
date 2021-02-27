package com.example.locktrust.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.locktrust.R;

import com.example.locktrust.Utils.AppDataHolder;
import com.example.locktrust.Utils.Helper;
import com.example.locktrust.interfaces.DialogListener;
import com.example.locktrust.interfaces.ToolbarActionListener;
import com.example.locktrust.widgets.CustomDialogManager;
import com.example.locktrust.widgets.CustomTextViewHeavy;
import com.example.locktrust.widgets.CustomTextViewMedium;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.ObjectAnimator;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.locktrust.Utils.AppDataHolder.mContext;


public class BaseActivity extends AppCompatActivity implements View.OnClickListener, DialogListener {
    public Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private CircleImageView ivUserProfilePic;
    private ViewGroup viewGroup;
    public View footer;
    private RelativeLayout rlProfile;
    public ImageView ivMenu, ivCart, ivFooterHome, ivFooterCoursesTutors, ivFooterWallet, ivMyCourses;
    private CustomTextViewHeavy tvUserName;
    private TextView tvHome, tvMyCourses, tvWishList, tvMyCart, tvlocktrustCredits,
            tvHelp, tvPrivacyPolicy, tvTermsConditions, tvDoesDonts, tvDespute,tvRefundPayment, tvPayRefund,tvAboutUs,tvcomplaints,
            tvMenuName, tvFooterCoursesTutors, tvFooterWallet, tvFooterMyCourses,tvFaq,tvMyTutor,tvCounsling;
    public TextView tvCartItemCount, tvFooterHome;
    private LinearLayout llLogout, llHome, llFooterWallet, llFooterMyCourses,layoutMyCourse;
    private LinearLayout llFooterCoursesTutors,layoutHome;
    private FrameLayout fbLive;
    public FrameLayout flCart,flDownload;
    private boolean mIsBackVisible;
    private int mimgNo = 0;
    private String profileImage="";
    public int screenNumber = 0;

    private ToolbarActionListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_base);
        viewGroup = (ViewGroup) findViewById(R.id.container);
        viewGroup.removeAllViews();
        View view = getLayoutInflater().inflate(layoutResID, null);
        viewGroup.addView(view);
        initialiseToolbar();
    }

    private void initialiseToolbar() {
        toolbar = findViewById(R.id.toolbar);
        tvMenuName = toolbar.findViewById(R.id.menu_name);
        ivMenu = toolbar.findViewById(R.id.ivMenu);
        ivCart = toolbar.findViewById(R.id.ivCart);
        tvCartItemCount = toolbar.findViewById(R.id.tvCartItemCount);
        rlProfile = findViewById(R.id.rlProfile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.requestDisallowInterceptTouchEvent(true);
        mDrawerLayout.setClickable(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_more_vert_24);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        flCart = toolbar.findViewById(R.id.flCart);
        flDownload = toolbar.findViewById(R.id.flDownload);
        footer = findViewById(R.id.footer);
        tvRefundPayment = findViewById(R.id.tvRefundPayment);
        llHome = footer.findViewById(R.id.llHome);
        llFooterCoursesTutors = footer.findViewById(R.id.llFooterCoursesTutors);
        llFooterWallet = footer.findViewById(R.id.llFooterWallet);
        llFooterMyCourses = footer.findViewById(R.id.llFooterMyCourses);
        fbLive = footer.findViewById(R.id.fbLive);

        tvFooterHome = footer.findViewById(R.id.tvFooterHome);
        tvFooterCoursesTutors = footer.findViewById(R.id.tvFooterCoursesTutors);
        tvFooterWallet = footer.findViewById(R.id.tvFooterWallet);
        tvFooterMyCourses = footer.findViewById(R.id.tvFooterMyCourses);

        ivFooterCoursesTutors = footer.findViewById(R.id.ivFooterCoursesTutors);
        ivFooterHome = footer.findViewById(R.id.ivFooterHome);
        ivFooterWallet = footer.findViewById(R.id.ivFooterWallet);
        ivMyCourses = footer.findViewById(R.id.ivMyCourses);

        //  vHome = footer.findViewById(R.id.vHome);

        ivUserProfilePic = (CircleImageView) findViewById(R.id.ivUserProfilePic);
        tvUserName = (CustomTextViewHeavy) findViewById(R.id.tvUserName);
        tvHome = (TextView) findViewById(R.id.tvHome);
        tvMyCourses = (TextView) findViewById(R.id.tvMyCourses);
        tvWishList = (TextView) findViewById(R.id.tvWishList);
        tvMyCart = (TextView) findViewById(R.id.tvMyCart);
        tvlocktrustCredits = (TextView) findViewById(R.id.tvlocktrustCredits);
        tvHelp = (TextView) findViewById(R.id.tvHelp);
        tvPrivacyPolicy = (TextView) findViewById(R.id.tvPrivacyPolicy);
        tvTermsConditions = (TextView) findViewById(R.id.tvTermsConditions);
        tvDoesDonts = (TextView) findViewById(R.id.tvDoesDonts);
        tvDespute = (TextView) findViewById(R.id.tvDespute);
        tvPayRefund = (TextView) findViewById(R.id.tvPayRefund);
        tvAboutUs = (TextView) findViewById(R.id.tvAboutUs);
        tvFaq = (TextView) findViewById(R.id.tvFaq);
        llLogout = (LinearLayout) findViewById(R.id.llLogout);
        tvcomplaints = (TextView) findViewById(R.id.tvcomplaints);
        tvMyTutor = (TextView) findViewById(R.id.tvMyTutor);
        tvCounsling = (TextView)findViewById(R.id.tvCounsling);
        layoutHome = (LinearLayout)findViewById(R.id.layoutHome);
        layoutMyCourse = (LinearLayout)findViewById(R.id.layoutMYcourse);
        tvHome.setOnClickListener(this);
        tvMyCourses.setOnClickListener(this);
        tvFooterMyCourses.setOnClickListener(this);
        tvWishList.setOnClickListener(this);
        tvMyCart.setOnClickListener(this);
        tvlocktrustCredits.setOnClickListener(this);
        tvHelp.setOnClickListener(this);
        tvPrivacyPolicy.setOnClickListener(this);
        tvTermsConditions.setOnClickListener(this);
        tvDoesDonts.setOnClickListener(this);
        tvDespute.setOnClickListener(this);
        tvPayRefund.setOnClickListener(this);
        tvAboutUs.setOnClickListener(this);
        llLogout.setOnClickListener(this);
        tvFooterCoursesTutors.setOnClickListener(this);
        llHome.setOnClickListener(this);
        tvcomplaints.setOnClickListener(this);
        tvMyTutor.setOnClickListener(this);
        llFooterCoursesTutors.setOnClickListener(this);
        llFooterWallet.setOnClickListener(this);
        llFooterMyCourses.setOnClickListener(this);
        fbLive.setOnClickListener(this);
        ivFooterCoursesTutors.setOnClickListener(this);
        ivCart.setOnClickListener(this);
        rlProfile.setOnClickListener(this);
        ivMyCourses.setOnClickListener(this);
        tvFooterMyCourses.setOnClickListener(this);
        tvFaq.setOnClickListener(this);
        tvRefundPayment.setOnClickListener(this);
        tvCounsling.setOnClickListener(this);
        layoutMyCourse.setVisibility(View.GONE);

        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.flipcomplete);
        anim.setTarget(layoutHome);
        anim.setDuration(2000);
        anim.start();
        ObjectAnimator anim1 = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.flipcomplete);

        layoutMyCourse.setVisibility(View.VISIBLE);
        anim1.setTarget(layoutMyCourse);
        anim1.setDuration(3000);
        anim1.start();


        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                toolbar.setNavigationIcon(R.drawable.ic_back);
                //  BaseActivity.this.getParent().requestVisibleBehind();
                // getParent().requestDisallowInterceptTouchEvent(true);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // lvSecondary.setVisibility(View.GONE);
                if (mIsBackVisible) {
                    toolbar.setNavigationIcon(R.drawable.ic_back);
                } else {
                    toolbar.setNavigationIcon(R.drawable.ic_baseline_more_vert_24);
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });

        //This function is used for navigation drawer menu
        functionDrawerMenuName();


        functionProfileUpdate();
        //here we hide show navigation drawer and footer menu
        if(AppDataHolder.getSession(this).getUserType().equals("parent"))
        {
            llFooterCoursesTutors.setVisibility(View.VISIBLE);
            llFooterWallet.setVisibility(View.VISIBLE);

            tvMyTutor.setVisibility(View.GONE);

        }else //children
        {
            llFooterCoursesTutors.setVisibility(View.GONE);
            llFooterWallet.setVisibility(View.GONE);


/*
            tvMyCart.setVisibility(View.GONE);
            tvlocktrustCredits.setVisibility(View.GONE);
            tvHelp.setVisibility(View.GONE);
            tvFaq.setVisibility(View.GONE);
            tvPrivacyPolicy.setVisibility(View.GONE);
            tvTermsConditions.setVisibility(View.GONE);
            tvDoesDonts.setVisibility(View.GONE);
            tvDespute.setVisibility(View.GONE);
            tvPayRefund.setVisibility(View.GONE);
            tvRefundPayment.setVisibility(View.GONE);
            tvAboutUs.setVisibility(View.GONE);
            tvcomplaints.setVisibility(View.GONE);
            tvMyTutor.setVisibility(View.VISIBLE);
            flCart.setVisibility(View.GONE);*/
        }

    }

    public void functionProfileUpdate() {
        RequestOptions placeholderRequest = new RequestOptions();
        placeholderRequest.placeholder(R.drawable.placeholder);
        if(AppDataHolder.getSession(this).getUserImage().contains("http"))
        {
            profileImage=AppDataHolder.getSession(this).getUserImage();
            //ivUserProfilePic

        }else
        {
            profileImage=Helper.imagProfileUrl+AppDataHolder.getSession(this).getUserImage();
        }
        Glide.with(getApplicationContext()).setDefaultRequestOptions(placeholderRequest).load(profileImage).into(ivUserProfilePic);
    }

    //count cart show

    //This function is used for navigation drawer menu
    private void functionDrawerMenuName() {
        tvUserName.setText(AppDataHolder.getSession(this).getUserName());
      /*  tvHome.setText(AppDataHolder.getSession(this).getLanguageText().getHome());
        tvSeries.setText(AppDataHolder.getSession(this).getLanguageText().getSeries());
        tvTrailors.setText(AppDataHolder.getSession(this).getLanguageText().getTrailers());
        tvMyTransactions.setText(AppDataHolder.getSession(this).getLanguageText().getMyTransactions());
        tvNotification.setText(AppDataHolder.getSession(this).getLanguageText().getNotifications());
        tvSettings.setText(AppDataHolder.getSession(this).getLanguageText().getSetting());
        tvLogOut.setText(AppDataHolder.getSession(this).getLanguageText().getLogout());

        tvFooterHome.setText(AppDataHolder.getSession(this).getLanguageText().getHome());
        tvFooterSeries.setText(AppDataHolder.getSession(this).getLanguageText().getSeries());
        tvFooterSearch.setText(AppDataHolder.getSession(this).getLanguageText().getSearch());
        tvFooterTrailars.setText(AppDataHolder.getSession(this).getLanguageText().getTrailers());
        tvMyCourses.setText(AppDataHolder.getSession(this).getLanguageText().getContact());*/


    }

    public void initialiseToolbar(String title, int imgNo, boolean isBackVisible, boolean isOptionMenu, boolean isSearch, String subTitle, ToolbarActionListener listener) {

        if (isSearch) {
            if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }

        } else {

            toolbar.setTitle(null);

            mListener = listener;
            mIsBackVisible = isBackVisible;
            mimgNo = imgNo;
            if (isBackVisible) {
                Log.e("ValueOFBackVisible", "True");
                toolbar.setNavigationIcon(R.drawable.ic_back);

                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

            } else {
                Log.e("ValueOFBackVisible", "False");
                toolbar.setNavigationIcon(R.drawable.ic_baseline_more_vert_24);
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                // TO disable  drawer swipe
            }

           /* toolbar.setNavigationOnClickListener(view ->

            {
                if (mIsBackVisible) {
                    finish();

                } else {
                    //Toast.makeText(this, "Navigation Menu call", Toast.LENGTH_SHORT).show();
                    if (!mIsBackVisible) {
                        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                            mDrawerLayout.closeDrawer(Gravity.LEFT);
                        } else {
                            mDrawerLayout.openDrawer(Gravity.LEFT);
                        }
                    } else {
                        mListener.onBackPressed();
                    }
                }

            });*/

        }
        if (null != title) {
            tvMenuName.setVisibility(View.VISIBLE);
            tvMenuName.setText(title);
        }

        if(title.equalsIgnoreCase("Cart")){
            tvCartItemCount.setVisibility(View.GONE);
        }
    }

    public void hideFooter() {
        footer.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!mIsBackVisible) {
                    if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                        mDrawerLayout.closeDrawer(Gravity.LEFT);
                    } else {
                        mDrawerLayout.openDrawer(Gravity.LEFT);
                    }
                } else {
                    mListener.onBackPressed();

                    finish();
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
        else {
            if (mListener != null)
                mListener.onBackPressed();
            finish();
        }
    }

    //Onclick listener function
    @Override
    public void onClick(View v) {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        switch (v.getId()) {
            case R.id.llHome:
            case R.id.tvHome:
                if (screenNumber != 1) {
                    finishAffinity();
                    startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, HomeActivity.class));
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }

                break;
         /*   case R.id.ivMyCourses:
            case R.id.tvFooterMyCourses:
            case R.id.llFooterMyCourses:*/
            case R.id.tvMyCourses:
                if (screenNumber != 2) {
                    finishAffinity();
                    startActivity(new Intent(BaseActivity.this, InvoiceActivity.class));
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }


                break;
            case R.id.ivCart:
            case R.id.tvMyCart:
                if (screenNumber != 4) {

                    //startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, CartActivity.class));
                } else {

                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }

                break;
            case R.id.tvHelp:
                if (screenNumber != 6) {
                   // startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, ContactUsActivity.class));
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }

                break;
            case R.id.tvPrivacyPolicy:
                if (screenNumber != 7) {
                  //  startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, PrivacyPolicyActivity.class));
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }

                break;
            case R.id.tvTermsConditions:
                if (screenNumber != 8) {
                  //  startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, TermsAndConditionsActivity.class));
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
                break;

            case R.id.tvDoesDonts:
                if (screenNumber != 9) {
                   // startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, DoAndDontPolicyActivity.class));
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }

                break;
            case R.id.tvDespute:
                if (screenNumber != 10) {
                  //  startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, DesputePolicyActivity.class));
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }

                break;
            case R.id.tvPayRefund:
                if (screenNumber != 11) {
                   // startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, PayRefundPolicyActivity.class));
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }

                break;
            case R.id.tvAboutUs:
                if (screenNumber != 12) {
                   // startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, AboutUsActivity.class));
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }

                break;
            case R.id.tvcomplaints:
              //  startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, ComplaintsActivity.class));
                break;

            case R.id.tvCounsling:
               // startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, FreeCounselingActivity.class));
                break;

            case R.id.tvMyTutor:



                break;

            case R.id.ivFooterCoursesTutors:
            case R.id.llFooterCoursesTutors:
            case R.id.tvFooterCoursesTutors:



                break;
            case R.id.tvFaq:

                if (screenNumber != 14) {
                   // startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, FAQActivity.class));
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }

                break;
            case R.id.tvlocktrustCredits:
            case R.id.llFooterWallet:

                if (screenNumber != 15) {
                   // startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, WalletActivity.class));
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }

                break;

            case R.id.llLogout:
                CustomDialogManager.showOkCancelDialog(this, "Do you want to logout?", com.example.locktrust.Activities.BaseActivity.this, "");

                break;
        case R.id.tvWishList:
            if( screenNumber!=18)
            {
               // startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, WishListActivity.class));
            }


            break;

            case R.id.rlProfile:
                if (screenNumber != 16) {

                  //  startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, ParentProfileActivity.class));
                }
                break;

            case R.id.fbLive:
                if (screenNumber != 17) {
                  //  startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this, LiveActivity.class));
                }
                break;

            case  R.id.tvRefundPayment:
               // startActivity(new Intent(com.example.locktrust.Activities.BaseActivity.this,RefundPaymentActivity.class));
                break;
            default:
                Toast.makeText(this, R.string.under_construction, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void selectFooterMenu(int selectFooterMenu) {
        if (selectFooterMenu == 2) {

            ivFooterHome.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_home));
            ivFooterCoursesTutors.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_course_selected));
            ivFooterWallet.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_wallet));
            ivMyCourses.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_course));

            tvFooterHome.setTextColor(this.getResources().getColor(R.color.darkgrey));
            tvFooterCoursesTutors.setTextColor(this.getResources().getColor(R.color.skybluedark));
            tvFooterWallet.setTextColor(this.getResources().getColor(R.color.darkgrey));
            tvFooterMyCourses.setTextColor(this.getResources().getColor(R.color.darkgrey));

        } else if (selectFooterMenu == 3) {
            ivFooterHome.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_home));
            ivFooterCoursesTutors.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_course));
            ivFooterWallet.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_wallet));
            ivMyCourses.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_course_selected));

            tvFooterHome.setTextColor(this.getResources().getColor(R.color.darkgrey));
            tvFooterCoursesTutors.setTextColor(this.getResources().getColor(R.color.darkgrey));
            tvFooterWallet.setTextColor(this.getResources().getColor(R.color.darkgrey));
            tvFooterMyCourses.setTextColor(this.getResources().getColor(R.color.skybluedark));
        } else if (selectFooterMenu == 4) {
            ivFooterHome.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_home));
            ivFooterCoursesTutors.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_course));
            ivFooterWallet.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_wallet_selected));
            ivMyCourses.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_course));
            tvFooterHome.setTextColor(this.getResources().getColor(R.color.darkgrey));
            tvFooterCoursesTutors.setTextColor(this.getResources().getColor(R.color.darkgrey));
            tvFooterWallet.setTextColor(this.getResources().getColor(R.color.skybluedark));
            tvFooterMyCourses.setTextColor(this.getResources().getColor(R.color.darkgrey));


        }else if (selectFooterMenu == 5) {
            ivFooterHome.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_home));
            ivFooterCoursesTutors.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_course));
            ivFooterWallet.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_wallet));
            ivMyCourses.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_course));
            tvFooterHome.setTextColor(this.getResources().getColor(R.color.darkgrey));
            tvFooterCoursesTutors.setTextColor(this.getResources().getColor(R.color.darkgrey));
            tvFooterWallet.setTextColor(this.getResources().getColor(R.color.darkgrey));
            tvFooterMyCourses.setTextColor(this.getResources().getColor(R.color.darkgrey));


        } else {
            ivFooterHome.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_home_selected));
            ivFooterCoursesTutors.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_course));
            ivFooterWallet.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_wallet));
            ivMyCourses.setImageDrawable(this.getResources().getDrawable(R.drawable.ic_course));

            tvFooterHome.setTextColor(this.getResources().getColor(R.color.skybluedark));
            tvFooterCoursesTutors.setTextColor(this.getResources().getColor(R.color.darkgrey));
            tvFooterWallet.setTextColor(this.getResources().getColor(R.color.darkgrey));
            tvFooterMyCourses.setTextColor(this.getResources().getColor(R.color.darkgrey));
        }

    }

    //logout function
    private void removeUserDetails() {
        AppDataHolder.getSession(this).setAccessToken("");
        AppDataHolder.getSession(this).setUserId("");
        AppDataHolder.getSession(this).setUserName("");
        AppDataHolder.getSession(this).setUserContact("");
        AppDataHolder.getSession(this).setUserEmail("");
        AppDataHolder.getSession(this).setUserImage("");
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        Helper.functionDeleteCourseCart(this);//remove all the cart courses
        finishAffinity();
        startActivity(new Intent(this, com.example.locktrust.Activities.LoginActivity.class));
    }

    @Override
    public void onButtonClicked(int type) {

    }

    @Override
    public void onButtonClicked(int type, String dialog_ref) {
        if (type == Dialog.BUTTON_POSITIVE) {
            removeUserDetails();
        }
    }
}