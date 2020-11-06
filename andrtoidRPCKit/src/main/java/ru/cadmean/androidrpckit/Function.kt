package ru.cadmean.androidrpckit


public class Function internal constructor(public val name: String, public val client: RpcClient) {

    public inline fun <reified TResult> call(vararg functionArguments: Any?, noinline onComplete: (TResult?, Int) -> Unit) {
        val auth = client.configuration.authorizationTicketHolder.ticket?.accessToken;
        val call = FunctionCall(arrayOf(*functionArguments), auth)

        val data = client.configuration.codecProvider.encode(call)
        val url = "${client.serverUrl}/${client.configuration.functionUrlProvider.getUrl(this)}"

        client.configuration.transportProvider.send(url, data, client.configuration.codecProvider.contentType) { responseData, error ->
            if (error != 0) {
                onComplete(null, error)
                return@send
            }

            val output = client.configuration.codecProvider.decode<FunctionOutput<TResult>>(responseData)

            if (output.metaData != null) {
                if (output.metaData.containsKey("resultType") &&
                        output.metaData["resultType"] == RpcDataType.Auth.strCode &&
                        output.result is JwtAuthorizationTicket) {
                    client.configuration.authorizationTicketHolder.ticket = output.result
                }
            }

            onComplete(output.result, output.error)
        }
    }
}
