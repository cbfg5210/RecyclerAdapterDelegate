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
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * This delegate provide method to hook in this delegate to {@link RecyclerView.Adapter} lifecycle.
 * This "hook in" mechanism is provided by {@link AdapterDelegatesManager} and that is the
 * component
 * you have to use.
 *
 * @param <T> The type of the data source element
 * @author Hannes Dorfmann
 * @since 1.0
 */
public interface AdapterDelegate<T> {

    /**
     * Called to determine whether this AdapterDelegate is the responsible for the given data
     * element.
     *
     * @param item The element of data source
     * @return true, if this item is responsible,  otherwise false
     */
    boolean isForViewType(@NonNull T item);

    /**
     * Creates the  {@link RecyclerView.ViewHolder} for the given data source item
     *
     * @param parent The ViewGroup parent of the given data source
     * @return The new instantiated {@link RecyclerView.ViewHolder}
     */
    @NonNull
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    /**
     * Called to bind the {@link RecyclerView.ViewHolder} to the item of the data source set
     *
     * @param holder The {@link RecyclerView.ViewHolder} to bind
     * @param item   The element of data source
     */
    void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull T item);
}
