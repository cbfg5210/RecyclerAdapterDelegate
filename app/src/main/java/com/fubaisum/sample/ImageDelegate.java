package com.fubaisum.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.fubaisum.adapterdelegate.AbsAdapterDelegate;
import com.fubaisum.adapterdelegate.OnDelegateClickListener;
import com.fubaisum.sample.model.ImageItem;
import com.fubaisum.sample.model.Item;

/**
 * Created by sum on 5/10/16.
 */
public class ImageDelegate extends AbsAdapterDelegate<Item, ImageDelegate.ViewHolder> {

    public ImageDelegate(Activity activity) {
        super(activity, R.layout.layout_image);
    }

    @Override
    public boolean isForViewType(@NonNull Item item) {
        return item instanceof ImageItem;
    }

    @Override
    protected ViewHolder onCreateViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull Item item, @NonNull ViewHolder holder) {
        ImageItem imageItem = (ImageItem) item;

        holder.ivIcon.setImageResource(imageItem.imageRes != 0 ? imageItem.imageRes : R.mipmap.ic_launcher);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivIcon;

        private OnDelegateClickListener onDelegateClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            ivIcon = (ImageView) itemView.findViewById(R.id.iv_main_item_icon);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (onDelegateClickListener != null) {
                onDelegateClickListener.onClick(v, position);
            }
        }

        public void setOnDelegateClickListener(OnDelegateClickListener onDelegateClickListener) {
            this.onDelegateClickListener = onDelegateClickListener;
        }
    }

}
