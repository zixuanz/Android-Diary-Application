package com.zixuanz.mysecretdiary.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zixuanz.mysecretdiary.DataStructures.Home.Diary;
import com.zixuanz.mysecretdiary.R;

import java.util.List;

/**
 * Created by Zixuan Zhao on 2/19/17.
 */

public class HomeRecyViewAdapter extends RecyclerView.Adapter {

    private final static int WITHOUT_IMG = 0;
    private final static int WITH_IMG = 1;

    private List<Diary> diaries;
    private Context context;

    public HomeRecyViewAdapter(List<Diary> diaries, Context context) {
        this.diaries = diaries;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case WITH_IMG:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_adp_hm_noimg, parent, false);
                break;
            default:
            case WITHOUT_IMG:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_adp_hm_noimg, parent, false);
                break;
        }

        return new WithoutImageHolder(view);
    }

    @Override
    public int getItemCount() {
        return diaries.isEmpty() ? 0 : diaries.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(diaries.get(position).getImgID()==-1){
            return WITHOUT_IMG;
        }
        return WITH_IMG;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof WithoutImageHolder){
            ((WithoutImageHolder)holder).date.setText(diaries.get(position).getDate().toString());
            ((WithoutImageHolder)holder).ctx.setText(diaries.get(position).getDiaryText());
        }
    }

    private static class WithoutImageHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView date;
        TextView ctx;

        public WithoutImageHolder(View view){
            super(view);

            this.cardView = (CardView) view.findViewById(R.id.cv_recy_hm);
            this.date = (TextView) view.findViewById(R.id.tv_recy_hm_date);
            this.ctx = (TextView) view.findViewById(R.id.tv_recy_hm_ctx);
        }
    }


}