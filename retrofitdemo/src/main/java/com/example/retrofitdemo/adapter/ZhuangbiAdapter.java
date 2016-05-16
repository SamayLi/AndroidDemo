package com.example.retrofitdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.bean.ZhuangbiImage;

import java.util.List;

/**
 * Created by shaohua.li on 5/16/16.
 */
public class ZhuangbiAdapter extends RecyclerView.Adapter {

    List<ZhuangbiImage> images;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        ZhuangbiImage image=images.get(position);
        Glide.with(viewHolder.itemView.getContext()).load(image.image_url).into(viewHolder.imageView);
        viewHolder.textView.setText(image.description);
    }

    @Override
    public int getItemCount() {
        return images==null?0:images.size();
    }

    public void setImages(List<ZhuangbiImage> images) {
        this.images = images;
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.imageIv);
            textView= (TextView) itemView.findViewById(R.id.descriptionTv);
        }
    }
}
