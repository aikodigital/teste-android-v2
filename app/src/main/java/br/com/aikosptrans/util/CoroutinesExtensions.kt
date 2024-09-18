package br.com.aikosptrans.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun <Data> CoroutineScope.launchSuspend(
    block: suspend CoroutineScope.() -> Data,
    onLoading: (Boolean) -> Unit = {},
    onSuccess: (Data) -> Unit = {},
    onError: (Throwable) -> Unit = {}
) {
    launch(Dispatchers.IO) {
        onLoading(true)
        try {
            val result = block()
            onLoading(false)
            onSuccess(result)
        } catch (e: Exception) {
            onLoading(false)
            onError(e)
        }
    }
}