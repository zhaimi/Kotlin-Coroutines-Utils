package com.safframework.kotlin.coroutines

import com.safframework.kotlin.coroutines.exception.UncaughtCoroutineExceptionHandler
import com.safframework.kotlin.coroutines.listener.CoroutineErrorListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

/**
 *
 * @FileName:
 *          com.safframework.kotlin.coroutines.SafeCoroutineScope
 * @author: Tony Shen
 * @date: 2019-10-19 12:43
 * @version: V1.0 安全的 CoroutineScope，支持异常处理
 */
class SafeCoroutineScope(context: CoroutineContext, errorHandler: CoroutineErrorListener?=null) : CoroutineScope,
    Closeable {

    override val coroutineContext: CoroutineContext = SupervisorJob() + context + UncaughtCoroutineExceptionHandler(errorHandler)

    override fun close() {
        coroutineContext.cancelChildren()
    }
}