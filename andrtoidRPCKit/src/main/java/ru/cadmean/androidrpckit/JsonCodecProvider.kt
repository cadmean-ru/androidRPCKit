package ru.cadmean.androidrpckit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

public class JsonCodecProvider : CodecProvider {
    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        .create()

    override fun encode(src: Any): ByteArray {
        return gson.toJson(src).toByteArray()
    }

    override fun decode(data: ByteArray, resultType: Type): Any {
        return gson.fromJson(String(data), resultType)
    }

    override val contentType: String
        get() = "application/json"
}