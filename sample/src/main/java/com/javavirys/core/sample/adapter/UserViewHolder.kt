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

package com.javavirys.core.sample.adapter

import android.view.ViewGroup
import com.javavirys.core.entity.User
import com.javavirys.core.presentation.adapter.BaseViewHolder
import com.javavirys.core.sample.R
import com.javavirys.core.sample.databinding.ViewUserItemBinding

class UserViewHolder(parent: ViewGroup) :
    BaseViewHolder<User, ViewUserItemBinding>(parent, R.layout.view_user_item) {

    override fun bind(item: User) = with(requireBinding()) {
        name.text = item.name
    }
}