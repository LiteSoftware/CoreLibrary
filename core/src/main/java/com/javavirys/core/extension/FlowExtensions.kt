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
import com.javavirys.core.exception.BaseException
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

suspend fun <T> FlowCollector<Result<T>>.emitError(throwable: Throwable) =
    emit(throwable.asResult())

suspend fun <T> FlowCollector<Result<T>>.emitSuccess(value: T) =
    emit(value.asResult())

suspend inline fun <T, R> Flow<T>.collectWithReturnResult(crossinline action: suspend (value: T) -> R): R {
    var result: R? = null
    collect {
        result = action(it)
    }
    return result!!
}

fun <T> Flow<T>.asResult() = map { Result.Success(it) }

fun <T> T.toFlow() = flow { emit(this@toFlow) }

fun <T> Flow<Result<T>>.filterResult(predicate: suspend (T) -> Boolean) = this.filter {
    if (it is Result.Success) {
        predicate(it.data)
    } else {
        false
    }
}

fun <T, R> Flow<Result<T>>.mapResult(transform: suspend (value: T) -> R): Flow<Result<R>> {
    return map {
        when (it) {
            is Result.Success -> Result.Success(transform.invoke(it.data))
            is Result.Error -> it
            is Result.Progress -> it
        }
    }
}

@FlowPreview
fun <T, R> Flow<Result<T>>.flatMapConcatResult(transform: suspend (value: T) -> Flow<Result<R>>): Flow<Result<R>> {
    return flatMapConcat {
        when (it) {
            is Result.Success -> transform(it.data)
            is Result.Error -> it.asFlow()
            is Result.Progress -> it.asFlow()
        }
    }
}

fun <T> Flow<Result<T>>.onEmptyResult(action: () -> Unit): Flow<Result<T>> {
    return onEmpty {
        try {
            action()
        } catch (exception: Exception) {
            emitError(exception)
        }
    }
}

fun <T> Flow<Result<T>>.toSingle() = flow<Result<T>> {
    this@toSingle.onSuccess {
        this@flow.emitSuccess(it)
        throw EndOfFlowMarker()
    }.onError {
        this@flow.emitError(it)
    }.catch { exception ->
        if (exception !is EndOfFlowMarker) this@flow.emitError(exception)
    }.collect()
}

fun <T> Flow<Result<List<T>>>.toIterative() = flow<Result<T>> {
    this@toIterative.onSuccess { it.forEach { item -> this@flow.emitSuccess(item) } }
        .onError { this@flow.emitError(it) }
        .catch { exception -> this@flow.emitError(exception) }
        .collect()
}

fun <T> Flow<List<T>>.toIterable() = flow {
    this@toIterable.onEach { list -> list.forEach { emit(it) } }
        .collect()
}

fun <T> Flow<Result<T>>.onSuccess(action: suspend (T) -> Unit): Flow<Result<T>> {
    return onEach {
        if (it is Result.Success) {
            action(it.data)
        }
    }
}

fun <T> Flow<Result<T>>.onError(action: suspend (Throwable) -> Unit): Flow<Result<T>> {
    return onEach {
        if (it is Result.Error) {
            action(it.throwable)
        }
    }
}

fun <T> createMutableSharedFlow(
    replay: Int = 0,
    extraBufferCapacity: Int = 0,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
): MutableSharedFlow<Result<T>> =
    MutableSharedFlow(replay, extraBufferCapacity, onBufferOverflow)

fun <T> MutableSharedFlow<Result<T>>.tryEmitSuccess(value: T) = tryEmit(Result.Success(value))

fun <T> MutableSharedFlow<Result<T>>.tryEmitError(throwable: Throwable) =
    tryEmit(Result.Error(throwable))

class EndOfFlowMarker : BaseException()