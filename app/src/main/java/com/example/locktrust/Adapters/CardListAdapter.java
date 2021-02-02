package com.example.locktrust.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locktrust.Model.ModelSample.CardListSample;
import com.example.locktrust.R;
import com.example.locktrust.interfaces.Callback;
import com.example.locktrust.widgets.CustomTextViewHeavy;
import com.example.locktrust.widgets.CustomTextViewMedium;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CoursesViewHolder> {



    private Context context;
    public List<CardListSample> cardList;
    public String strHrs = "", strMinutes = "", NoOftutorials = "";
    private Callback callback;


    public CardListAdapter(Context context, List<CardListSample> cardList) {
        this.context = context;
        this.cardList = cardList;
        // setHasStableIds(true);
    }


    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card_home, parent, false);
        CoursesViewHolder coursesViewHolder = new CoursesViewHolder(view);
        return coursesViewHolder;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position) {
      //  holder.tvBalanceValue.setText(cardList.get(position).getAmount());
       // holder.tvCardNo.setText(cardList.get(position).getCardNo());
        holder.tvHolderName.setText(cardList.get(position).getHolderName());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }


    public class CoursesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvtotalBalance)
        CustomTextViewMedium tvtotalBalance;
        @BindView(R.id.tvBalanceValue)
        CustomTextViewMedium tvBalanceValue;
        @BindView(R.id.tvCardNo)
        CustomTextViewHeavy tvCardNo;
        @BindView(R.id.tvHolderName)
        CustomTextViewHeavy tvHolderName;
        public CoursesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
