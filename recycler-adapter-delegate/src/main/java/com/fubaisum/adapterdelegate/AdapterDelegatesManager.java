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

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

import java.util.List;


class AdapterDelegatesManager<T> {

    /**
     * Map for ViewType to AdapterDelegate
     */
    SparseArrayCompat<AbsAdapterDelegate<T>> delegates = new SparseArrayCompat<>();

    /**
     * Adds an {@link AbsAdapterDelegate}.
     * <b>This method automatically assign internally the view type integer by using the next
     * unused</b>
     *
     * @param delegate the delegate to add
     * @return self
     * @throws NullPointerException if passed delegate is null
     */
    AdapterDelegatesManager<T> addDelegate(@NonNull AbsAdapterDelegate<T> delegate) {
        // algorithm could be improved since there could be holes,
        // but it's very unlikely that we reach Integer.MAX_VALUE and run out of unused indexes
        int itemViewType = delegates.size();
        delegates.put(itemViewType, delegate);
        return this;
    }

    int getItemViewType(List<T> items, int position) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            AbsAdapterDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(items.get(position))) {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No AdapterDelegate added that matches position=" + position + " in data source");
    }

    RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AbsAdapterDelegate<T> delegate = delegates.get(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }
        return delegate.onCreateViewHolder(parent);
    }

    void onBindViewHolder(RecyclerViewHolder viewHolder, T item) {
        AbsAdapterDelegate<T> delegate = delegates.get(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException(
                    "No AdapterDelegate added for ViewType " + viewHolder.getItemViewType());
        }
        delegate.onBindViewHolder(viewHolder, item);
    }

}
