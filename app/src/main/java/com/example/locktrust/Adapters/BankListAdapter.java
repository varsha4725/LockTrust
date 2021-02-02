package com.example.locktrust.Adapters;

import android.app.AppOpsManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locktrust.Model.ModelSample.HomeBankListSample;
import com.example.locktrust.R;
import com.example.locktrust.interfaces.Callback;
import com.example.locktrust.widgets.CustomTextViewMedium;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.CoursesViewHolder> {



    private Context context;
    public List<HomeBankListSample> banakList;
    public String strHrs = "", strMinutes = "", NoOftutorials = "";
    private Callback callback;


    public BankListAdapter(Context context, List<HomeBankListSample> banakList) {
        this.context = context;
        this.banakList = banakList;
        // setHasStableIds(true);
    }


    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_banking_list, parent, false);
        CoursesViewHolder coursesViewHolder = new CoursesViewHolder(view);
        return coursesViewHolder;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position) {
     holder.ivOptions.setImageResource(banakList.get(position).getImage());
     holder.tvOption.setText(banakList.get(position).getName());
    holder.ivOptions.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callback.myCallback(position," ");
        }
    });

    }

    @Override
    public int getItemCount() {
        return banakList.size();
    }


    public class CoursesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivOptions)
        ImageView ivOptions;
        @BindView(R.id.tvOption)
        CustomTextViewMedium tvOption;
        public CoursesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
