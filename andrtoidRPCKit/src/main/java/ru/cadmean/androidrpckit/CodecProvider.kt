package ru.cadmean.androidrpckit

import java.lang.reflect.Type

public interface CodecProvider {
    public fun encode(src: Any): ByteArray
    public fun decode(data: ByteArray, resultType: Type): Any
    public val contentType: String
}