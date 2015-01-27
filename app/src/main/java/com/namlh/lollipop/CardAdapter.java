package com.namlh.lollipop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.namlh.lollipop.dto.ListTTItems;
import com.namlh.lollipop.dto.TTItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by namlh on 11/21/14.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    public List<TTItem> posts = new ArrayList<>();

//    public void addItems(List<TTItem> items){
//        posts.addAll(items);
//        notifyDataSetChanged();
//    }

    public void addItem(TTItem item){
        posts.add(item);
        notifyDataSetChanged();
    }

    public void clear(){
        posts.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = View.inflate(context,R.layout.item_cardview,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(posts.get(position).title);
        Picasso.with(holder.mImageView.getContext()).load(posts.get(position).images[0]).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.textview);
            mImageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }
}
