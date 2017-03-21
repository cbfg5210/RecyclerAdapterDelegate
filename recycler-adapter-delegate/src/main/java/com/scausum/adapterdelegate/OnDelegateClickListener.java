package com.scausum.adapterdelegate;

import android.view.View;

/**
 *
 */
public interface OnDelegateClickListener {
    /**
     * @param view     the itemView or its child view
     * @param position the itemView's position in RecyclerView
     */
    void onClick(View view, int position);
}