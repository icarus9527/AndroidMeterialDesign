package com.example.yls.meterialdesign;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by icarus9527 on 2017/3/31.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<Anime> resource;

    public MainRecyclerAdapter(List<Anime> resource){
        this.resource = resource;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        if(context == null){context = parent.getContext();}
        View view = LayoutInflater.from(context).inflate(R.layout.main_recycler_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Anime anime = resource.get(position);
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("animeName",anime.getAnimeName());
                intent.putExtra("animePic",anime.getAnimePic());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Anime anime = resource.get(position);
        holder.textView.setText(anime.getAnimeName());
        Glide.with(context).load(anime.getAnimePic()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return resource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.main_rvItem_card);
            imageView = (ImageView) itemView.findViewById(R.id.main_rvItem_iv);
            textView = (TextView) itemView.findViewById(R.id.main_rvItem_tv);
        }
    }
}
