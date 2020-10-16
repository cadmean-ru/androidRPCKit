package ru.cadmean.androidrpckit

import com.google.gson.reflect.TypeToken

public class Function internal constructor(public val name: String, private val client: RpcClient) {

    public fun <TResult> call(vararg functionArguments: Any, onComplete: (TResult?, Int) -> Unit) {
        try {
            constructCallAndSend(arrayOf(*functionArguments)) { responseData ->
                val t = object : TypeToken<FunctionOutput<TResult>>() {}.type
                val output = client.configuration.codecProvider.decode(responseData, t) as FunctionOutput<TResult>

                onComplete(output.result, output.error)
            }
        } catch (ex: TransportException) {
            ex.printStackTrace()
            onComplete(null, 5)
        }
    }

    private fun constructCallAndSend(functionArguments: Array<Any>, onComplete: (ByteArray) -> Unit) {
        val call = FunctionCall(functionArguments, authorizeCallIfPossible())
        sendCall(call, onComplete)
    }

    private fun authorizeCallIfPossible(): String? {
        return client.configuration.authorizationTicketHolder.ticket?.accessToken
    }

    private fun sendCall(call: FunctionCall, onComplete: (ByteArray) -> Unit) {
        val data = client.configuration.codecProvider.encode(call)
        val url = "${client.serverUrl}/${client.configuration.functionUrlProvider.getUrl(this)}"
        client.configuration.transportProvider.send(
            url,
            data,
            client.configuration.codecProvider.contentType,
            onComplete,
        )
    }

    private fun processMetaData() {

    }
}