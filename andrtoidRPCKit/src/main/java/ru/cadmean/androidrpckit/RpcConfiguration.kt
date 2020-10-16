package ru.cadmean.androidrpckit

public class RpcConfiguration {
    public var codecProvider: CodecProvider = JsonCodecProvider()
    public var transportProvider: TransportProvider = HttpTransportProvider()
    public var functionUrlProvider: FunctionUrlProvider = DefaultFunctionUrlProvider()
    public var authorizationTicketHolder: AuthorizationTicketHolder =
        TransientAuthorizationTicketHolder()
}