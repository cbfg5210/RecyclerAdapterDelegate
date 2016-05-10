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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class AbsAdapterDelegate<T> {

    protected LayoutInflater layoutInflater;
    protected int itemLayoutResId;
    private int itemViewType;

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public AbsAdapterDelegate(Activity activity, int itemLayoutResId) {
        this.layoutInflater = LayoutInflater.from(activity);
        this.itemLayoutResId = itemLayoutResId;
    }

    public int getItemViewType() {
        return itemViewType;
    }

    public void setItemViewType(int itemViewType) {
        this.itemViewType = itemViewType;
    }

    protected abstract boolean isForViewType(T item);

    public final RecyclerViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = layoutInflater.inflate(itemLayoutResId, parent, false);
        final RecyclerViewHolder viewHolder = onCreateViewHolder(itemView);
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

    protected abstract RecyclerViewHolder onCreateViewHolder(View itemView);

    protected abstract void onBindViewHolder(RecyclerViewHolder holder, T item);


    /**
     * Item Click Listener
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    /**
     * Item Long Click Listener
     */
    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }
}
