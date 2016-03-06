/*
 * Copyright (c) 2015 Hannes Dorfmann.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.fubaisum.adapterdelegate;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link AdapterDelegate} implementation that already implements {@link
 * #getItemViewType()}
 *
 * @author Hannes Dorfmann
 */
public abstract class AbsAdapterDelegate<T> implements AdapterDelegate<T> {

    protected LayoutInflater layoutInflater;
    protected int layoutResId;
    protected int viewType;

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public AbsAdapterDelegate(Activity activity, int layoutResId, int viewType) {
        this.layoutInflater = LayoutInflater.from(activity);
        this.layoutResId = layoutResId;
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType() {
        return viewType;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = layoutInflater.inflate(layoutResId, parent, false);
        final RecyclerView.ViewHolder viewHolder = onCreateViewHolder(itemView);
        if (null != onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, viewHolder.getAdapterPosition());
                }
            });
        }
        if (null != onItemLongClickListener)
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onItemLongClick(view, viewHolder.getAdapterPosition());
                    return true;
                }
            });
        return viewHolder;
    }

    protected abstract RecyclerView.ViewHolder onCreateViewHolder(View itemView);

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }
}
