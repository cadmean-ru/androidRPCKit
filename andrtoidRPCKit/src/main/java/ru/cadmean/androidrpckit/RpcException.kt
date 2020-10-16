package ru.cadmean.androidrpckit

public open class RpcException(public val errorCode: Int, message: String) : Exception(message) {
    public constructor(errorCode: Int) : this(errorCode, "Function call failed with code: $errorCode")
}

public class TransportException(
    public val internalException: Exception? = null,
    message: String = "Failed to execute function due to transport error"
) : RpcException(5, message)

public class FunctionException(errorCode: Int) :
    RpcException(errorCode, "Function call resulted in an error: $errorCode")