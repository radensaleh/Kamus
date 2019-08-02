package com.example.rdsaleh.kamus.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rdsaleh.kamus.Model.KamusModel;
import com.example.rdsaleh.kamus.R;

import java.util.ArrayList;

public class AdapterENIN extends RecyclerView.Adapter<AdapterENIN.MyViewHolder> {

    private ArrayList<KamusModel> kamusModels = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;

    public AdapterENIN(Context mContext) {
        this.mContext = mContext;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.data_in, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.word.setText(kamusModels.get(i).getWord());
        myViewHolder.translate.setText(kamusModels.get(i).getTranslate());
    }

    @Override
    public int getItemCount() {
        return kamusModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView word, translate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            word = itemView.findViewById(R.id.tv_word);
            translate = itemView.findViewById(R.id.tv_translate);
        }
    }

    public void addItem(ArrayList<KamusModel> kamusModels){
        this.kamusModels = kamusModels;
        notifyDataSetChanged();
    }
}
