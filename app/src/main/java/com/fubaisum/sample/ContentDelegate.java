package com.fubaisum.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.fubaisum.adapterdelegate.AbsAdapterDelegate;
import com.fubaisum.adapterdelegate.OnDelegateClickListener;
import com.fubaisum.adapterdelegate.RecyclerViewHolder;
import com.fubaisum.sample.model.ContentItem;
import com.fubaisum.sample.model.Item;

/**
 * Created by sum on 5/10/16.
 */
public class ContentDelegate extends AbsAdapterDelegate<Item, RecyclerViewHolder> {

    private OnDelegateClickListener onDelegateClickListener;

    public ContentDelegate(Activity activity) {
        super(activity, R.layout.layout_content);
    }

    public void setOnDelegateClickListener(OnDelegateClickListener onDelegateClickListener) {
        this.onDelegateClickListener = onDelegateClickListener;
    }

    @Override
    public boolean isForViewType(@NonNull Item item) {
        return item instanceof ContentItem;
    }

    @Override
    protected RecyclerViewHolder onCreateViewHolder(View itemView) {
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(itemView);
        viewHolder.setOnDelegateClickListener(onDelegateClickListener);
        viewHolder.setOnClickListener(R.id.tv_main_item_content);
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull Item item, @NonNull RecyclerViewHolder holder) {
        ContentItem contentItem = (ContentItem) item;

        TextView tvContent = holder.getView(R.id.tv_main_item_content);
        tvContent.setText(contentItem.content != null ? contentItem.content : "Hello World!!!");
    }

}
