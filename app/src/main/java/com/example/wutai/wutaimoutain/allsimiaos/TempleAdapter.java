package com.example.wutai.wutaimoutain.allsimiaos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Simiao_juti.Every_Simiao_Activity;

import java.util.List;

public class TempleAdapter extends RecyclerView.Adapter<TempleAdapter.ViewHolder> {
    private List<Integer> pictureIDList;
    private List<String>  nameList;
    public TempleAdapter(List pictureIDList,List nameList ) {
        this.pictureIDList = pictureIDList;
        this.nameList = nameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allsimiao_item_layout,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Every_Simiao_Activity.actionstart(v.getContext(),nameList.get(holder.getAdapterPosition()).substring(3));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.templeImage.setImageResource(pictureIDList.get(position));
        holder.templeText.setText(nameList.get(position));
    }

    @Override
    public int getItemCount() {
        return pictureIDList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView templeImage;
        TextView templeText;
        public ViewHolder(View itemView) {
            super(itemView);
            templeImage = itemView.findViewById(R.id.all_simiao_iv_item);
            templeText = itemView.findViewById(R.id.all_simiao_tv_item);
        }
    }
}
