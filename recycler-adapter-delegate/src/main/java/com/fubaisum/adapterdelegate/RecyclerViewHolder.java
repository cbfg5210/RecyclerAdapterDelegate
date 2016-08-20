package com.fubaisum.adapterdelegate;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by sum on 15-12-9.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private final SparseArray<View> views;
    private OnDelegateClickListener onDelegateClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (null == view) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }

    public void setOnClickListener(int viewId) {
        View view = retrieveView(viewId);
        view.setOnClickListener(this);
    }

    public void setOnDelegateClickListener(OnDelegateClickListener listener) {
        this.onDelegateClickListener = listener;
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
