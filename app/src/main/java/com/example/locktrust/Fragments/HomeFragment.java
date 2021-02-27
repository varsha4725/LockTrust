package com.example.locktrust.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locktrust.Activities.HomeActivity;
import com.example.locktrust.Adapters.BankListAdapter;
import com.example.locktrust.Adapters.CardListAdapter;
import com.example.locktrust.Model.ModelSample.CardListSample;
import com.example.locktrust.Model.ModelSample.HomeBankListSample;
import com.example.locktrust.R;
import com.example.locktrust.interfaces.Callback;
import com.example.locktrust.interfaces.DialogListener;
import com.example.locktrust.widgets.CustomButton;
import com.example.locktrust.widgets.CustomTextViewMedium;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements Callback {


    Unbinder unbinder;
    @BindView(R.id.rvBankList1)
    RecyclerView rvBankList1;
    private List<HomeBankListSample> homeBankListSamples, homeBankListSamples1, homeBankListSamples2;
    private BankListAdapter bankListAdapter;
    private CardListAdapter cardListAdapter;
    private List<CardListSample> cardListSamples;
    String mTitle = "Home";
    @BindView(R.id.rvCardList)
    RecyclerView rvCardList;
    @BindView(R.id.tvOptionWallet)
    CustomTextViewMedium tvOptionWallet;
    @BindView(R.id.tvOptionGateway)
    CustomTextViewMedium tvOptionGateway;
    @BindView(R.id.lyOptions)
    LinearLayout lyOptions;
    @BindView(R.id.rvBankList)
    RecyclerView rvBankList;
    @BindView(R.id.rvBillPayments)
    RecyclerView rvBillPayments;
    @BindView(R.id.rvTools)
    RecyclerView rvTools;
    @BindView(R.id.ivDashboardIcon)
    ImageView ivDashboardIcon;
    HomeActivity context;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment*/
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        initArray();
        init();//initialization method
        return view;
    }

    // Initialize all array here
    private void initArray() {
        if (null == homeBankListSamples) {
            homeBankListSamples = new ArrayList<>();
        }
        if (null == homeBankListSamples1) {
            homeBankListSamples1 = new ArrayList<>();
        }
        if (null == homeBankListSamples2) {
            homeBankListSamples2 = new ArrayList<>();
        }
        if (null == cardListSamples) {
            cardListSamples = new ArrayList<>();
        }
    }

    public void init() {
        initCardList();
        initBankList();
        initBillPayment();
        initTools();
    }

    private void initCardList() {
        initRecycleViewCard();
        initRecyclerViewFlip();
        initDataCard();
    }


    private void initDataCard() {
        cardListSamples.add(new CardListSample(3456, 5675, "Sagar Kotak"));
        cardListSamples.add(new CardListSample(4532, 3434, "Varsha Bhole"));
        cardListSamples.add(new CardListSample(5667, 5678, "Leena Nerkar"));
        cardListSamples.add(new CardListSample(1254, 8775, "Sagar Kotak"));
    }

    private void initRecycleViewCard() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvCardList.setLayoutManager(linearLayoutManager);
        cardListAdapter = new CardListAdapter(getActivity(), cardListSamples);
        rvCardList.setHasFixedSize(true);
        rvCardList.setAdapter(cardListAdapter);
    }

    private void initTools() {
        initRecyclerView();
        initDatabankList();
    }

    private void initBillPayment() {
        initRecyclerViewBillPyment();
        initDatabankListPayment();
    }

    private void initDatabankListPayment() {
        homeBankListSamples2.add(new HomeBankListSample(R.drawable.phone_bill, "PHONE BILL"));
        homeBankListSamples2.add(new HomeBankListSample(R.drawable.light_bill, "LIGHT BILL"));
        homeBankListSamples2.add(new HomeBankListSample(R.drawable.flight, "FLIGHT"));
        homeBankListSamples2.add(new HomeBankListSample(R.drawable.others, "OTHERS"));
    }

    private void initRecyclerViewBillPyment() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvBillPayments.setLayoutManager(linearLayoutManager);
        bankListAdapter = new BankListAdapter(getActivity(), homeBankListSamples2);
        bankListAdapter.setCallback(this);
        rvBillPayments.setHasFixedSize(true);
        rvBillPayments.setAdapter(bankListAdapter);
    }

    // Initialization Method for bank List
    private void initBankList() {
        initRecyclerViewFlip();
        initRecyclerViewTools();
        initDatabankListTools();
    }

    private void initDatabankListTools() {
        homeBankListSamples1.add(new HomeBankListSample(R.drawable.calendar, "CALENDAR"));
        homeBankListSamples1.add(new HomeBankListSample(R.drawable.exchange, "EXCHANGE"));
        homeBankListSamples1.add(new HomeBankListSample(R.drawable.cards, "CARDS"));
        homeBankListSamples1.add(new HomeBankListSample(R.drawable.others, "OTHERS"));
    }

    public void initRecyclerViewTools() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvTools.setLayoutManager(linearLayoutManager);
        bankListAdapter = new BankListAdapter(getActivity(), homeBankListSamples1);
        bankListAdapter.setCallback(this);
        rvTools.setHasFixedSize(true);
        rvTools.setAdapter(bankListAdapter);

    }

    private void initDatabankList() {
        homeBankListSamples.add(new HomeBankListSample(R.drawable.add_money, "ADD MONEY"));
        homeBankListSamples.add(new HomeBankListSample(R.drawable.send_money, "SEND MONEY"));
        homeBankListSamples.add(new HomeBankListSample(R.drawable.invoice, "INVOICE MANAGEMENT"));
        homeBankListSamples.add(new HomeBankListSample(R.drawable.transaction, "TRANSACTION HISTORY"));
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvBankList.setLayoutManager(linearLayoutManager);
        bankListAdapter = new BankListAdapter(getActivity(), homeBankListSamples);
        rvBankList.setHasFixedSize(true);
        bankListAdapter.setCallback(this);
        rvBankList.setAdapter(bankListAdapter);

    }
    private void initRecyclerViewFlip() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvBankList1.setLayoutManager(linearLayoutManager);
        bankListAdapter = new BankListAdapter(getActivity(), homeBankListSamples);
        bankListAdapter.setCallback(this);
        rvBankList1.setHasFixedSize(true);
        rvBankList1.setAdapter(bankListAdapter);

    }

    @Override
    public void myCallback(int position, String flag) {
        ShowPopup(getContext());
    }

    public static void ShowPopup(Context context) {
        if (context != null) {
            AlertDialog.Builder build = new AlertDialog.Builder(context);
            final AlertDialog okCancelDialog;
            View view = LayoutInflater.from(context).inflate(R.layout.layout_popup_options, null);
            view.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in_anim));
            build.setView(view);
            okCancelDialog = build.create();
            okCancelDialog.getWindow().getAttributes().windowAnimations = R.anim.fade_in_anim;
            okCancelDialog.show();
        }
    }
}
