package ru.cadmean.androidrpckit

public enum class RpcErrorCode(public val code: Int) {
    FunctionNotCallable(-100),
    FunctionNotFound(-101),
    IncompatibleRpcVersion(-102),

    InvalidFunctionArguments(-200),

    FailedToEncode(-300),
    FailedToDecode(-301),

    FailedToSendCall(-400),
    NotSuccessfulStatusCode(-401),

    InternalServerError(-500),

    NotAuthorized(-600),

    NiceError(-69)
}