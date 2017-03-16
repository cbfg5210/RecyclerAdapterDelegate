package com.scausum.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scausum.adapterdelegate.AbsAdapterDelegate;
import com.scausum.adapterdelegate.OnDelegateClickListener;
import com.scausum.sample.model.ContentItem;
import com.scausum.sample.model.Item;

/**
 * Created by sum on 5/10/16.
 */
public class ContentDelegate extends AbsAdapterDelegate<Item> {

    private OnDelegateClickListener onDelegateClickListener;

    public ContentDelegate(Activity activity) {
        super(activity);
    }

    public void setOnDelegateClickListener(OnDelegateClickListener onDelegateClickListener) {
        this.onDelegateClickListener = onDelegateClickListener;
    }

    @Override
    public boolean isForViewType(@NonNull Item item) {
        return item instanceof ContentItem;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        View itemView = layoutInflater.inflate(R.layout.layout_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnDelegateClickListener(onDelegateClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, @NonNull Item item) {
        ViewHolder viewHolder = (ViewHolder) vh;
        ContentItem contentItem = (ContentItem) item;

        viewHolder.tvContent.setText(contentItem.content != null ? contentItem.content : "Hello World!!!");
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvContent;

        private OnDelegateClickListener onDelegateClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            tvContent = (TextView) itemView.findViewById(R.id.tv_main_item_content);
            tvContent.setOnClickListener(this);
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
