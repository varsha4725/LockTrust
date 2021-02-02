package com.example.locktrust.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locktrust.Adapters.BankListAdapter;
import com.example.locktrust.Adapters.InvoiceListAdapter;
import com.example.locktrust.Adapters.InvoiceOptionAdapter;
import com.example.locktrust.Model.ModelSample.CardListSample;
import com.example.locktrust.Model.ModelSample.HomeBankListSample;
import com.example.locktrust.Model.ModelSample.InvoiceListSample;
import com.example.locktrust.Model.ModelSample.InvoiceOptionSample;
import com.example.locktrust.R;
import com.example.locktrust.widgets.CustomButton;

import org.eazegraph.lib.charts.PieChart;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvoiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvoiceFragment extends Fragment {
    private List<InvoiceListSample> invoiceListSamples;
    private List<InvoiceOptionSample> invoiceOptionSamples;
    private InvoiceListAdapter invoiceListAdapter;
    private InvoiceOptionAdapter invoiceOptionAdapter;

    // Create the object of TextView and PieChart class

    PieChart pieChart;
    @BindView(R.id.rvInvoiceType)
    RecyclerView rvInvoiceType;
    @BindView(R.id.btnGnerateInvoiice)
    CustomButton btnGnerateInvoiice;
    @BindView(R.id.rvInvoiceList)
    RecyclerView rvInvoiceList;

    Unbinder unbinder;
    View view;
    public InvoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_invoice, container, false);
        unbinder = ButterKnife.bind(this, view);

        pieChart = view.findViewById(R.id.piechart);
        pieChart.startAnimation();
        initArray();
        init();
        return view;
    }

    public void initArray(){
        if (null == invoiceListSamples) {
            invoiceListSamples = new ArrayList<>();
        }        if (null == invoiceOptionSamples) {
            invoiceOptionSamples = new ArrayList<>();
        }

    }
    public void init(){
        initRecycleInvoiceOption();
        dataOption();
        initRecycleInvoiceList();
        initDataInvoiceList();
    }
    public  void  initRecycleInvoiceList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvInvoiceList.setLayoutManager(linearLayoutManager);
        invoiceListAdapter = new InvoiceListAdapter(getActivity(), invoiceListSamples);
      //  invoiceListAdapter.setCallback(this);
        rvInvoiceList.setHasFixedSize(true);
        rvInvoiceList.setAdapter(invoiceListAdapter);
    }

    public  void  initRecycleInvoiceOption(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvInvoiceType.setLayoutManager(linearLayoutManager);
        invoiceOptionAdapter = new InvoiceOptionAdapter(getActivity(), invoiceOptionSamples);
      //  invoiceListAdapter.setCallback(this);
        rvInvoiceType.setHasFixedSize(true);
        rvInvoiceType.setAdapter(invoiceOptionAdapter);
    }

    public  void dataOption(){
        invoiceOptionSamples.add(new InvoiceOptionSample("Sent"));
        invoiceOptionSamples.add(new InvoiceOptionSample("Receive"));
        invoiceOptionSamples.add(new InvoiceOptionSample("Pending"));
        invoiceOptionSamples.add(new InvoiceOptionSample("Requested"));
    }

    private void initDataInvoiceList() {
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
        invoiceListSamples.add(new InvoiceListSample("varsha@gmail.com","12243","23/12/2020"));
    }

    ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(mContext, R.animator.flipping);
    anim.setTarget(A View Object reference goes here i.e. ImageView);
    anim.setDuration(3000);
    anim.start();
}