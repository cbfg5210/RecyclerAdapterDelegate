package com.scausum.adapterdelegate;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by sum on 3/21/17.
 */

public abstract class OnDelegateLongClickListener implements View.OnLongClickListener {
    private RecyclerView.ViewHolder viewHolder;

    public void setViewHolder(RecyclerView.ViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    @Override
    public final boolean onLongClick(View v) {
        if (viewHolder == null) {
            throw new NullPointerException("Must invoke OnDelegateClickListener.setViewHolder() method in " +
                    "before View.onLongClick() event.");
        }
        int position = viewHolder.getAdapterPosition();
        return onLongClick(v, position);
    }

    /**
     * Callback when long click the itemView's child view
     *
     * @param child    the itemView's child view
     * @param position the itemView's position in RecyclerView
     */
    protected abstract boolean onLongClick(View child, int position);
}
