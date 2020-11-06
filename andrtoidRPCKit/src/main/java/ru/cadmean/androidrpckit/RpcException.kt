package ru.cadmean.androidrpckit

import java.io.IOException

public open class RpcException(public val errorCode: Int, message: String) : IOException(message) {
    public constructor(errorCode: Int) : this(errorCode, "Function call failed with code: $errorCode")
}

public class TransportException(
    public val internalException: Exception? = null,
    message: String = "Failed to execute function due to transport error"
) : RpcException(RpcErrorCode.FailedToSendCall.code, message)

public class FunctionException(errorCode: Int) :
    RpcException(errorCode, "Function call resulted in an error: $errorCode")