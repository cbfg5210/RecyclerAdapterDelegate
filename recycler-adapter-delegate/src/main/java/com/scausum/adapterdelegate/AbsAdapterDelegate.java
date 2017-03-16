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

package com.scausum.adapterdelegate;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class AbsAdapterDelegate<T> implements AdapterDelegate<T> {

    protected LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public AbsAdapterDelegate(Activity activity) {
        this.layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public abstract boolean isForViewType(@NonNull T item);

    @NonNull
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        final RecyclerView.ViewHolder viewHolder = onCreateViewHolder(layoutInflater, parent);
        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, viewHolder.getAdapterPosition());
                }
            });
        }
        if (onItemLongClickListener != null)
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onItemLongClick(view, viewHolder.getAdapterPosition());
                    return true;
                }
            });
        return viewHolder;
    }

    protected abstract RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup parent);


    /**
     * Click itemView listener.
     */
    public interface OnItemClickListener {
        /**
         * Callback when click the itemView
         *
         * @param itemView itemView in RecyclerView.ViewHolder
         * @param position the itemView's position in RecyclerView
         */
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    /**
     * Long click itemView listener
     */
    public interface OnItemLongClickListener {
        /**
         * Callback when long click the itemView
         *
         * @param itemView itemView in RecyclerView.ViewHolder
         * @param position the itemView's position in RecyclerView
         */
        void onItemLongClick(View itemView, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }

}
