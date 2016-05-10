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
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * This class is the element that ties {@link RecyclerView.Adapter} together with {@link
 * AbsAdapterDelegate}.
 * <p>
 * So you have to add / register your {@link AbsAdapterDelegate}s to this manager by calling {@link
 * #addDelegate(AbsAdapterDelegate)}
 * </p>
 *
 * @param <T> The type of the datasource of the adapter
 * @author Hannes Dorfmann
 */
class AdapterDelegatesManager<T> {

    SparseArrayCompat<AbsAdapterDelegate<T>> delegates = new SparseArrayCompat<AbsAdapterDelegate<T>>();

    public AdapterDelegatesManager<T> addDelegate(AbsAdapterDelegate<T> delegate) {

        int itemViewType = delegates.size();
        delegate.setItemViewType(itemViewType);
        delegates.put(itemViewType, delegate);
        return this;
    }

    /**
     * Must be called from {@link AbsDelegationAdapter#getItemViewType(int)}. Internally it scans all
     * the registered {@link AbsAdapterDelegate} and picks the right one to return the ViewType integer.
     *
     * @param items    Adapter's data source
     * @param position the position in adapters data source
     * @return the ViewType (integer)
     *
     * @throws IllegalArgumentException if no {@link AbsAdapterDelegate} has been found that is responsible
     *         for the given data element in data set (No {@link AbsAdapterDelegate} for the given ViewType)
     *
     * @throws NullPointerException     if items is null
     */
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

    /**
     * This method must be called in {@link AbsDelegationAdapter#onCreateViewHolder(ViewGroup, int)}
     *
     * @param parent   the parent
     * @param viewType the view type
     * @return The new created ViewHolder
     * @throws NullPointerException if no AdapterDelegate has been registered for ViewHolders viewType
     */
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AbsAdapterDelegate<T> delegate = delegates.get(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }

        return delegate.onCreateViewHolder(parent);
    }

    /**
     * Must be called from{@link AbsDelegationAdapter#onBindViewHolder(RecyclerViewHolder, int)}
     *
     * @param viewHolder the ViewHolder to bind
     * @param item      Adapter's data source item
     * @throws NullPointerException if no AdapterDelegate has been registered for ViewHolders viewType
     */
    public void onBindViewHolder(RecyclerViewHolder viewHolder, T item) {

        AbsAdapterDelegate<T> delegate = delegates.get(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException(
                    "No AdapterDelegate added for ViewType " + viewHolder.getItemViewType());
        }

        delegate.onBindViewHolder(viewHolder,item);
    }

}
