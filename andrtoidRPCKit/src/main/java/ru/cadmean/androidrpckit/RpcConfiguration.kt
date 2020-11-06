package ru.cadmean.androidrpckit

public class RpcConfiguration {
    public val codecProvider: KotlinxJsonCodecProvider = KotlinxJsonCodecProvider()
    public var transportProvider: TransportProvider = HttpTransportProvider()
    public var functionUrlProvider: FunctionUrlProvider = DefaultFunctionUrlProvider()
    public var authorizationTicketHolder: AuthorizationTicketHolder =
        TransientAuthorizationTicketHolder()
}