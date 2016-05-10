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

import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

import java.util.List;


class AdapterDelegatesManager<T> {

    SparseArrayCompat<AbsAdapterDelegate<T>> delegates = new SparseArrayCompat<>();

    public AdapterDelegatesManager<T> addDelegate(AbsAdapterDelegate<T> delegate) {

        int itemViewType = delegates.size();
        delegate.setItemViewType(itemViewType);
        delegates.put(itemViewType, delegate);
        return this;
    }

    public int getItemViewType(List<T> items, int position) {

        int delegatesCount = delegates.size();
        AbsAdapterDelegate<T> delegate;
        for (int i = 0; i < delegatesCount; i++) {
            delegate = delegates.valueAt(i);
            if (delegate.isForViewType(items.get(position))) {
                return delegate.getItemViewType();
            }
        }

        throw new IllegalArgumentException(
                "No AdapterDelegate added that matches position=" + position + " in data source");
    }

    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AbsAdapterDelegate<T> delegate = delegates.get(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }

        return delegate.onCreateViewHolder(parent);
    }

    public void onBindViewHolder(RecyclerViewHolder viewHolder, T item) {

        AbsAdapterDelegate<T> delegate = delegates.get(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException(
                    "No AdapterDelegate added for ViewType " + viewHolder.getItemViewType());
        }

        delegate.onBindViewHolder(viewHolder, item);
    }

}
