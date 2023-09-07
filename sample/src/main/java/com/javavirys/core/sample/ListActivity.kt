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

package com.javavirys.core.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.javavirys.core.entity.User
import com.javavirys.core.sample.adapter.UserAdapter

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var adapter: UserAdapter? = null

        with(findViewById<RecyclerView>(R.id.recyclerView)) {
            adapter = UserAdapter {
                adapter?.selectItem(it)
            }.also {
                it.addItem(User("Nick"))
                it.addItem(User("Slava"))
                it.addItem(User("Lee"))
                it.addItem(User("Tony"))
                it.addItem(User("Tony1"))
                it.addItem(User("Tony2"))
                it.addItem(User("Tony3"))
                it.addItem(User("Tony4"))
                it.addItem(User("Tony5"))
                it.addItem(User("Tony6"))
                it.addItem(User("Tony7"))
                it.addItem(User("Tony8"))
            }
            this.adapter = adapter
        }
    }
}