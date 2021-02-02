package com.example.locktrust.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locktrust.Model.ModelSample.InvoiceListSample;
import com.example.locktrust.Model.ModelSample.InvoiceOptionSample;
import com.example.locktrust.R;
import com.example.locktrust.interfaces.Callback;

import java.util.List;

import butterknife.ButterKnife;

public class InvoiceOptionAdapter extends RecyclerView.Adapter<InvoiceOptionAdapter.CoursesViewHolder> {



    private Context context;
    public List<InvoiceOptionSample> invoiceOptionSamples;
    private Callback callback;


    public InvoiceOptionAdapter(Context context, List<InvoiceOptionSample> invoiceOptionSamples) {
        this.context = context;
        this.invoiceOptionSamples = invoiceOptionSamples;
        // setHasStableIds(true);
    }


    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_option_list, parent, false);
        CoursesViewHolder coursesViewHolder = new CoursesViewHolder(view);
        return coursesViewHolder;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position) {
       holder.tvOption.setText(invoiceOptionSamples.get(position).getStrOption());

    }

    @Override
    public int getItemCount() {
        return invoiceOptionSamples.size();
    }


    public class CoursesViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOption;

        public CoursesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOption = (TextView)itemView.findViewById(R.id.tvOptionInvoice);
            ButterKnife.bind(this, itemView);
        }
    }
}
