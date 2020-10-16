package ru.cadmean.androidrpckit

import com.google.gson.Gson
import java.lang.reflect.Type

public class JsonCodecProvider : CodecProvider {
    private val gson = Gson()

    override fun encode(src: Any): ByteArray {
        return gson.toJson(src).toByteArray()
    }

    override fun decode(data: ByteArray, resultType: Type): Any {
        return gson.fromJson(String(data), resultType)
    }

    override val contentType: String
        get() = "application/json"
}