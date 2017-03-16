package com.scausum.adapterdelegate;

import android.view.View;

/**
 * Click itemView's children view listener
 */
public interface OnDelegateClickListener {
    /**
     * Callback when click the itemView's child view
     *
     * @param child    the itemView's child view
     * @param position the itemView's position in RecyclerView
     */
    void onClick(View child, int position);
}