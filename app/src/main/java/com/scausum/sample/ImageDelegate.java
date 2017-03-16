package com.scausum.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scausum.adapterdelegate.AbsAdapterDelegate;
import com.scausum.sample.model.ImageItem;
import com.scausum.sample.model.Item;

/**
 * Created by sum on 5/10/16.
 */
public class ImageDelegate extends AbsAdapterDelegate<Item> {

    public ImageDelegate(Activity activity) {
        super(activity);
    }

    @Override
    public boolean isForViewType(@NonNull Item item) {
        return item instanceof ImageItem;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        View itemView = layoutInflater.inflate(R.layout.layout_image, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull Item item) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ImageItem imageItem = (ImageItem) item;

        viewHolder.ivIcon.setImageResource(imageItem.imageRes != 0 ? imageItem.imageRes : R.mipmap.ic_launcher);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            ivIcon = (ImageView) itemView.findViewById(R.id.iv_main_item_icon);
        }
    }

}
