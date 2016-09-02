package com.fubaisum.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.fubaisum.adapterdelegate.AbsAdapterDelegate;
import com.fubaisum.adapterdelegate.OnDelegateClickListener;
import com.fubaisum.sample.model.ContentItem;
import com.fubaisum.sample.model.Item;

/**
 * Created by sum on 5/10/16.
 */
public class ContentDelegate extends AbsAdapterDelegate<Item, ContentDelegate.ViewHolder> {

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
    protected ViewHolder onCreateViewHolder(View itemView) {
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnDelegateClickListener(onDelegateClickListener);
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull Item item, @NonNull ViewHolder holder) {
        ContentItem contentItem = (ContentItem) item;
        holder.tvContent.setText(contentItem.content != null ? contentItem.content : "Hello World!!!");
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
