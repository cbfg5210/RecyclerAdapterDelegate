package com.fubaisum.sample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fubaisum.adapterdelegate.AbsAdapterDelegate;
import com.fubaisum.adapterdelegate.RecyclerViewHolder;
import com.fubaisum.sample.model.ComplexItem;
import com.fubaisum.sample.model.Item;

/**
 * Created by sum on 5/10/16.
 */
public class ComplexDelegate extends AbsAdapterDelegate<Item,RecyclerViewHolder> {

    public ComplexDelegate(Activity activity) {
        super(activity);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.layout_complex;
    }

    @Override
    public boolean isForViewType(@NonNull Item item) {
        return item instanceof ComplexItem;
    }

    @Override
    protected RecyclerViewHolder onCreateViewHolder(View itemView) {
        return new RecyclerViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull Item item, @NonNull RecyclerViewHolder holder) {
        ComplexItem complexItem = (ComplexItem) item;

        TextView tvContent = holder.getView(R.id.tv_main_item_content);
        ImageView ivIcon = holder.getView(R.id.iv_main_item_icon);

        tvContent.setText(complexItem.content != null ? complexItem.content : "Hello World!!!");
        ivIcon.setImageResource(complexItem.imageRes != 0 ? complexItem.imageRes : R.mipmap.ic_launcher);
    }
}
