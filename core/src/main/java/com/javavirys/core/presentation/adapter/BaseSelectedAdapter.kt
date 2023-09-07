/*
 * Copyright 2023 Vitaliy Sychov
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

import androidx.databinding.ViewDataBinding

abstract class BaseSelectedAdapter<E, VH : BaseSelectedViewHolder<E, out ViewDataBinding>> :
    BaseAdapter<E, VH>() {

    private var selectedItem: Pair<E?, Int?>? = null

    fun selectItem(item: E?): Int {
        val oldSelectedItem = selectedItem
        val oldSelectedItemIndex = selectedItem?.second ?: 0
        items.forEachIndexed { index, e ->
            if (compareItems(item, e)) {
                selectedItem = item to index
                notifyItemChanged(index)
                if (compareItems(oldSelectedItem?.first, items[oldSelectedItemIndex])) {
                    notifyItemChanged(oldSelectedItemIndex)
                    return index
                }
            } else if (compareItems(oldSelectedItem?.first, e)) {
                notifyItemChanged(index)
            }
        }
        return selectedItem?.second ?: -1
    }

    open fun compareItems(item1: E?, item2: E?): Boolean = item1 == item2

    override fun onBindViewHolder(holder: VH, position: Int) {
        super.onBindViewHolder(holder, position)
        val isSelectedItem = compareItems(selectedItem?.first, items[position])
        holder.onSelectedItem(isSelectedItem)
    }
}