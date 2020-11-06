package ru.cadmean.androidrpckit

public interface TransportProvider {
    public fun send(url: String, data: ByteArray, contentType: String, onComplete: (ByteArray, Int) -> Unit)
}