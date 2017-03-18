package com.scausum.adapterdelegate;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *
 */
public abstract class OnDelegateClickListener
        implements View.OnClickListener, View.OnLongClickListener {

    private RecyclerView.ViewHolder viewHolder;

    public void setViewHolder(RecyclerView.ViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    @Override
    public void onClick(View v) {
        if (viewHolder == null) {
            return;
        }
        int position = viewHolder.getAdapterPosition();
        onClick(v, position);
    }

    @Override
    public boolean onLongClick(View v) {
        return true;
    }

    /**
     * Callback when click the itemView's child view
     *
     * @param child    the itemView's child view
     * @param position the itemView's position in RecyclerView
     */
    abstract void onClick(View child, int position);
}