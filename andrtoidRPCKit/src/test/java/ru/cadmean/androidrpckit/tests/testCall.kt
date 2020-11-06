package ru.cadmean.androidrpckit.tests

import ru.cadmean.androidrpckit.Function
import java.util.concurrent.CompletableFuture

internal inline fun <reified TResult> Function.testCall(vararg functionArguments: Any?, noinline onComplete: (TResult?, Int) -> Unit) {
    val f = CompletableFuture<TResult?>()
    this.call<TResult>(*functionArguments) { res, err ->
        onComplete(res, err)
        f.complete(res)
        if (err == 0)
            println("Test call to function ${this.name} finished with result: $res")
        else
            println("Test call to function ${this.name} finished with error: $err")
    }
    f.get()
}