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

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.javavirys.core.extension.inflate

@Suppress("MemberVisibilityCanBePrivate")
abstract class BaseViewHolder<E, VB : ViewDataBinding>(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
) : RecyclerView.ViewHolder(parent.inflate(layoutId)) {

    protected val bindingMaybeNullable: VB? by lazy { DataBindingUtil.bind(itemView) }

    protected val binding: VB by lazy { bindingMaybeNullable!! }

    abstract fun bind(item: E)
}