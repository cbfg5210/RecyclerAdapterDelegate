package com.fubaisum.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fubaisum.adapterdelegate.AbsAdapterDelegate;
import com.fubaisum.adapterdelegate.OnDelegateClickListener;
import com.fubaisum.sample.model.ComplexItem;
import com.fubaisum.sample.model.Item;

/**
 * Created by sum on 5/10/16.
 */
public class ComplexDelegate extends AbsAdapterDelegate<Item, ComplexDelegate.ComplexViewHolder> {

    private OnDelegateClickListener onDelegateClickListener;

    public ComplexDelegate(Activity activity) {
        super(activity, R.layout.layout_complex);
    }

    public void setOnDelegateClickListener(OnDelegateClickListener onDelegateClickListener) {
        this.onDelegateClickListener = onDelegateClickListener;
    }

    @Override
    public boolean isForViewType(@NonNull Item item) {
        return item instanceof ComplexItem;
    }

    @Override
    protected ComplexViewHolder onCreateViewHolder(View itemView) {
        ComplexViewHolder complexViewHolder = new ComplexViewHolder(itemView);
        complexViewHolder.setOnDelegateClickListener(onDelegateClickListener);
        return complexViewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull Item item, @NonNull ComplexViewHolder holder) {

        ComplexItem complexItem = (ComplexItem) item;

        holder.tvContent.setText(complexItem.content != null ? complexItem.content : "Hello World!!!");
        holder.ivIcon.setImageResource(complexItem.imageRes != 0 ? complexItem.imageRes : R.mipmap.ic_launcher);
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
