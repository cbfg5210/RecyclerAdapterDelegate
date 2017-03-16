package com.scausum.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scausum.adapterdelegate.AbsAdapterDelegate;
import com.scausum.adapterdelegate.OnDelegateClickListener;
import com.scausum.sample.model.ComplexItem;
import com.scausum.sample.model.Item;

/**
 * Created by sum on 5/10/16.
 */
public class ComplexDelegate extends AbsAdapterDelegate<Item> {

    private OnDelegateClickListener onDelegateClickListener;

    public ComplexDelegate(Activity activity) {
        super(activity);
    }

    public void setOnDelegateClickListener(OnDelegateClickListener onDelegateClickListener) {
        this.onDelegateClickListener = onDelegateClickListener;
    }

    @Override
    public boolean isForViewType(@NonNull Item item) {
        return item instanceof ComplexItem;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        View itemView = layoutInflater.inflate(R.layout.layout_complex, parent, false);
        ComplexViewHolder complexViewHolder = new ComplexViewHolder(itemView);
        complexViewHolder.setOnDelegateClickListener(onDelegateClickListener);
        return complexViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull Item item) {
        ComplexViewHolder complexViewHolder = (ComplexViewHolder) viewHolder;
        ComplexItem complexItem = (ComplexItem) item;

        complexViewHolder.tvContent.setText(complexItem.content != null ? complexItem.content : "Hello World!!!");
        complexViewHolder.ivIcon.setImageResource(complexItem.imageRes != 0 ? complexItem.imageRes : R.mipmap.ic_launcher);
    }

    static class ComplexViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView tvContent;
        private ImageView ivIcon;

        private OnDelegateClickListener onDelegateClickListener;

        ComplexViewHolder(View itemView) {
            super(itemView);

            tvContent = (TextView) itemView.findViewById(R.id.tv_main_item_content);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_main_item_icon);

            tvContent.setOnClickListener(this);
        }

        void setOnDelegateClickListener(OnDelegateClickListener onDelegateClickListener) {
            this.onDelegateClickListener = onDelegateClickListener;
        }

        @Override
        public void onClick(View v) {
            if (onDelegateClickListener == null) {
                return;
            }
            int position = getAdapterPosition();
            onDelegateClickListener.onClick(v, position);
        }
    }
}
