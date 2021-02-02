package com.example.locktrust.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locktrust.Model.ModelSample.HomeBankListSample;
import com.example.locktrust.Model.ModelSample.InvoiceListSample;
import com.example.locktrust.R;
import com.example.locktrust.interfaces.Callback;
import com.example.locktrust.widgets.CustomTextViewMedium;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvoiceListAdapter extends RecyclerView.Adapter<InvoiceListAdapter.CoursesViewHolder> {



    private Context context;
    public List<InvoiceListSample> invoiceListSamples;
    public String strHrs = "", strMinutes = "", NoOftutorials = "";
    private Callback callback;


    public InvoiceListAdapter(Context context, List<InvoiceListSample> invoiceListSamples) {
        this.context = context;
        this.invoiceListSamples = invoiceListSamples;
        // setHasStableIds(true);
    }


    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_invoice_list, parent, false);
        CoursesViewHolder coursesViewHolder = new CoursesViewHolder(view);
        return coursesViewHolder;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position) {
        holder.tvmailId.setText(invoiceListSamples.get(position).getMailId());
        holder.tvAmount.setText(invoiceListSamples.get(position).getAmount());
        holder.tvDate.setText(invoiceListSamples.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return invoiceListSamples.size();
    }


    public class CoursesViewHolder extends RecyclerView.ViewHolder {
        private TextView tvmailId,tvAmount,tvDate;

        public CoursesViewHolder(@NonNull View itemView) {

            super(itemView);
            tvmailId = (TextView)itemView.findViewById(R.id.tvEmailIdInvoice);
            tvDate = (TextView)itemView.findViewById(R.id.tvDateInvoice);
            tvAmount = (TextView)itemView.findViewById(R.id.tvAmountInvoice);
            ButterKnife.bind(this, itemView);
        }
    }
}
