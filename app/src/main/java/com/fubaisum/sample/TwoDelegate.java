package com.fubaisum.sample;

import android.app.Activity;
import android.view.View;

import com.fubaisum.adapterdelegate.AbsAdapterDelegate;
import com.fubaisum.adapterdelegate.RecyclerViewHolder;

/**
 * Created by sum on 5/10/16.
 */
public class TwoDelegate extends AbsAdapterDelegate<Item>{

    public TwoDelegate(Activity activity) {
        super(activity, R.layout.layout_two);
    }

    @Override
    protected boolean isForViewType(Item item) {
        return item instanceof ItemTwo;
    }

    @Override
    protected RecyclerViewHolder onCreateViewHolder(View itemView) {
        return new RecyclerViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(RecyclerViewHolder holder, Item item) {

    }
}
