package ru.cadmean.androidrpckit.tests

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test
import ru.cadmean.androidrpckit.FunctionOutput
import ru.cadmean.androidrpckit.JwtAuthorizationTicket

public class SerializationTests {

    @Test
    public fun `Should decode function output`() {
        val str = """
        {
            "error": 0,
            "result": {
                "accessToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImVtYWlsQGV4YW1wbGUuY29tIiwibmJmIjoxNjA0NjEwNDIyLCJleHAiOjE2MDQ2MTQwMjIsImlzcyI6ImxvY2FsaG9zdDo1MDAwIiwiYXVkIjoibG9jYWxob3N0OjUwMDAifQ._q6HhdA6EbxcDvj5jcEX0rRDjhDanfBiSlsRgRMDELPIhf-UmvkUdkW32GDBqsslEL35lqGYQiFtagnSXimLpA",
                "refreshToken":"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImVtYWlsQGV4YW1wbGUuY29tIiwibmJmIjoxNjA0NjEwNDIyLCJleHAiOjE2MDQ2MTQwMjIsImlzcyI6ImxvY2FsaG9zdDo1MDAwIiwiYXVkIjoibG9jYWxob3N0OjUwMDAifQ._q6HhdA6EbxcDvj5jcEX0rRDjhDanfBiSlsRgRMDELPIhf-UmvkUdkW32GDBqsslEL35lqGYQiFtagnSXimLpA"
            },
            "metaData": {
                "resultType":"auth",
                "clrResultType":"Cadmean.RPC.JwtAuthorizationTicket",
                "internalException":null,
                "exceptionStackTrace":null
            }
        }
        """.trimIndent()

        val json = Json.decodeFromString<FunctionOutput<JwtAuthorizationTicket>>(str)

        print(json)
    }
}