package ru.cadmean.androidrpckit

import okhttp3.*
import java.io.IOException

public class HttpTransportProvider : TransportProvider {

    private val client: OkHttpClient = OkHttpClient()

    override fun send(
            url: String,
            data: ByteArray,
            contentType: String,
            onComplete: (ByteArray, Int) -> Unit
    ) {

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", contentType)
            .addHeader("Cadmean-RPC-Version", RpcClient.supportedRpcVersion)
            .post(RequestBody.create(MediaType.parse(contentType), data))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()

                onComplete(ByteArray(0), RpcErrorCode.FailedToSendCall.code)
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body()?.bytes() ?: ByteArray(0)

                onComplete(responseData, if (response.code() == 200) 0 else RpcErrorCode.NotSuccessfulStatusCode.code)
            }
        })
    }
}