package ru.cadmean.androidrpckit

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ArraySerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.*

public class CallArgumentsSerializer : KSerializer<Any?> {

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("asd", PrimitiveKind.BOOLEAN)

    override fun deserialize(decoder: Decoder): Any? {
        return null
    }


    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())

    @ExperimentalSerializationApi
    @Suppress("UNCHECKED_CAST")
    override fun serialize(encoder: Encoder, value: Any?) {
        when (value) {
            null -> encoder.encodeNull()
            is Short -> encoder.encodeShort(value)
            is Int -> encoder.encodeInt(value)
            is Long -> encoder.encodeLong(value)
            is Float -> encoder.encodeFloat(value)
            is Double -> encoder.encodeDouble(value)
            is String -> encoder.encodeString(value)
            is Boolean -> encoder.encodeBoolean(value)
            is Date -> encoder.encodeString(dateFormat.format(value))
            is List<*> -> encoder.encodeSerializableValue(ListSerializer(CallArgumentsSerializer()), value)
            is Array<*> -> encoder.encodeSerializableValue(ArraySerializer(CallArgumentsSerializer()), value as Array<Any?>)
            is Map<*, *> -> {
                val stringAnyMap: MutableMap<String, Any?> = HashMap()
                for ((key, value1) in value as Map<Any?, Any?>) {
                    if (key is String) {
                        stringAnyMap[key] = value1
                    } else {
                        throw RpcException(RpcErrorCode.FailedToEncode.code, "Maps with non-string keys are not supported")
                    }
                }
                encoder.encodeSerializableValue(MapSerializer(String.serializer(), CallArgumentsSerializer()), stringAnyMap)
            }
            else -> {
                // TODO("Serialize objects")
                throw RpcException(RpcErrorCode.FailedToEncode.code, "Classes are not yet supported")
            }
        }
    }
}