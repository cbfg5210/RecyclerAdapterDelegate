package com.fubaisum.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.fubaisum.adapterdelegate.AbsAdapterDelegate;
import com.fubaisum.adapterdelegate.RecyclerViewHolder;
import com.fubaisum.sample.model.Item;
import com.fubaisum.sample.model.ImageItem;

/**
 * Created by sum on 5/10/16.
 */
public class ImageDelegate extends AbsAdapterDelegate<Item, RecyclerViewHolder> {

    public ImageDelegate(Activity activity) {
        super(activity, R.layout.layout_image);
    }

    @Override
    public boolean isForViewType(@NonNull Item item) {
        return item instanceof ImageItem;
    }

    @Override
    protected RecyclerViewHolder onCreateViewHolder(View itemView) {
        return new RecyclerViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull Item item, @NonNull RecyclerViewHolder holder) {
        ImageItem imageItem = (ImageItem) item;

        ImageView ivIcon = holder.getView(R.id.iv_main_item_icon);
        ivIcon.setImageResource(imageItem.imageRes != 0 ? imageItem.imageRes : R.mipmap.ic_launcher);
    }

}
