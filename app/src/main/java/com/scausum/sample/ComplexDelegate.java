package com.scausum.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scausum.adapterdelegate.AdapterDelegate;
import com.scausum.adapterdelegate.OnDelegateClickListener;
import com.scausum.sample.model.ComplexItem;
import com.scausum.sample.model.Item;

import java.util.List;

/**
 * Created by sum on 5/10/16.
 */
public class ComplexDelegate extends AdapterDelegate<Item> {

    private OnDelegateClickListener onDelegateClickListener;

    public ComplexDelegate(Activity activity) {
        super(activity);
    }

    public void setOnDelegateClickListener(OnDelegateClickListener listener) {
        this.onDelegateClickListener = listener;
    }

    @Override
    public boolean isForViewType(@NonNull Item item) {
        return item instanceof ComplexItem;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent) {
        View itemView = layoutInflater.inflate(R.layout.layout_complex, parent, false);
        ComplexViewHolder complexViewHolder = new ComplexViewHolder(itemView);
        return complexViewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull Item item, @NonNull List<Object> payloads) {
        final ComplexViewHolder viewHolder = (ComplexViewHolder) holder;
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDelegateClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    onDelegateClickListener.onClick(v, position);
                }
            }
        };
        ComplexItem complexItem = (ComplexItem) item;
        viewHolder.tvContent.setOnClickListener(onClickListener);
        viewHolder.ivIcon.setOnClickListener(onClickListener);
        viewHolder.tvContent.setText(complexItem.content != null ? complexItem.content : "Hello World!!!");
        viewHolder.ivIcon.setImageResource(complexItem.imageRes != 0 ? complexItem.imageRes : R.mipmap.ic_launcher);
    }

    private static class ComplexViewHolder extends RecyclerView.ViewHolder {

        private TextView tvContent;
        private ImageView ivIcon;

        ComplexViewHolder(View itemView) {
            super(itemView);

            tvContent = (TextView) itemView.findViewById(R.id.tv_main_item_content);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_main_item_icon);
        }
    }
}
