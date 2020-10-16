package ru.cadmean.androidrpckit

import okhttp3.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

public class HttpTransportProvider : TransportProvider {

//    private val scope = MainScope()
    private val client: OkHttpClient = OkHttpClient()

    override fun send(
        url: String,
        data: ByteArray,
        contentType: String,
        onComplete: (ByteArray) -> Unit
    ) {

//        scope.launch {
//            withContext(Dispatchers.IO) {
//                val client = HttpClient()
//                val response: ResponseException
//                try {
//                    response = client.post {
//                        url(url)
//                        headers {
//                            header("Content-Type", contentType)
//                            header("Cadmean-RPC-Version", "2")
//                        }
//                        body = ByteArrayContent(data)
//                    }
//                } catch (ex: Exception) {
//                    println(ex)
//                    throw TransportException()
//                }

//                if (response.status != HttpStatusCode.OK)
//                    throw TransportException(
//                        message = "HTTP transport failed with response status: ${response.status}")
//                client.close()

        val request = Request.Builder()
            .url(url)
            .addHeader("Content-Type", contentType)
            .addHeader("Cadmean-RPC-Version", "2")
            .post(RequestBody.create(MediaType.parse(contentType), data))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                throw TransportException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code() != 200) {
                    throw TransportException(
                        message = "Server failed to respond with a successful status"
                    )
                }

                val responseData = response.body()?.bytes() ?: throw TransportException(
                    message = "Response body bru"
                )

                onComplete(responseData)
            }
        })
//            }
//        }
    }
}