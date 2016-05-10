package com.fubaisum.sample;

import android.app.Activity;
import android.view.View;

import com.fubaisum.adapterdelegate.AbsAdapterDelegate;
import com.fubaisum.adapterdelegate.RecyclerViewHolder;

/**
 * Created by sum on 5/10/16.
 */
public class ThreeDelegate extends AbsAdapterDelegate<Item>{

    public ThreeDelegate(Activity activity) {
        super(activity, R.layout.layout_three);
    }

    @Override
    protected boolean isForViewType(Item item) {
        return item instanceof ItemThree;
    }

    @Override
    protected RecyclerViewHolder onCreateViewHolder(View itemView) {
        return new RecyclerViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(RecyclerViewHolder holder, Item item) {

    }
}
