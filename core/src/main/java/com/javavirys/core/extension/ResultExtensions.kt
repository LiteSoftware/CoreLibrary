/*
 * Copyright 2021 Vitaliy Sychov. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.javavirys.core.extension

import com.javavirys.core.entity.Result
import kotlinx.coroutines.flow.flow

fun <T> Result<T>.asFlow() = flow { emit(this@asFlow) }

fun <T> T.asResult(): Result<T> = Result.Success(this)

fun Throwable.asResult() = Result.Error(this)
