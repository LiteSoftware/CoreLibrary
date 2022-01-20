/*
 * Copyright 2022 Vitaliy Sychov
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.javavirys.core.presentation.adapter

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<E, VB : ViewDataBinding, VH : BaseViewHolder<E, VB>> :
    RecyclerView.Adapter<VH>() {

    val items = mutableListOf<E>()

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun addItem(element: E) {
        items.add(element)
        notifyItemInserted(itemCount - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(elements: Collection<E>) {
        setItemsWithoutNotifyDataSetChanged(elements)
        notifyDataSetChanged()
    }

    fun setItemsWithoutNotifyDataSetChanged(elements: Collection<E>) {
        items.clear()
        items.addAll(elements)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeAllItems() = items.clear()
        .also {
            notifyDataSetChanged()
        }

    fun remove(item: E) = items.remove(item)
}