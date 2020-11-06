package ru.cadmean.androidrpckit.tests

import kotlinx.serialization.Serializable
import org.junit.Assert.*
import org.junit.Test
import ru.cadmean.androidrpckit.JwtAuthorizationTicket
import ru.cadmean.androidrpckit.RpcClient


public class SimpleFunctionCallTests {

    private val client = RpcClient("http://testrpc.cadmean.ru")

    @Test
    public fun `Should call sum`() {
        val a = 1
        val b = 2
        val expectedResult = 3
        val expectedError = 0

        client.f("sum").testCall<Int>(a, b) { res, err ->
            assertEquals(expectedError, err)
            assertEquals(expectedResult, res)
        }
    }

    @Test
    public fun `Should call square`() {
        val a = 0.3
        val expectedResult = 0.09
        val expectedError = 0

        client.f("square").testCall<Double>(a) { res, err ->
            assertEquals(expectedError, err)
            assertEquals(expectedResult, res!!, 0.01)
        }
    }

    @Test
    public fun `Should call concat`() {
        val a = "Hello,"
        val b = " world!"
        val expectedResult = "Hello, world!"
        val expectedError = 0

        client.f("concat").testCall<String>(a, b) { res, err ->
            assertEquals(expectedError, err)
            assertEquals(expectedResult, res)
        }
    }

    @Test
    public fun `Should call getDate`() {
        val expectedError = 0

        client.f("concat").testCall<String> { res, err ->
            assertTrue(res is String)
            assertEquals(expectedError, err)
        }
    }


    @Serializable
    public data class User(public var email: String, public var name: String)

    @Test
    public fun `Should call auth`() {
        val expectedError = 0
        val expectedResult = User("email@example.com", "George")

        client.f("auth").testCall<JwtAuthorizationTicket>("email@example.com", "password") { res, err ->
            if (err != 0) {
                return@testCall
            }
            client.f("user.get").testCall<User> { res1, err1 ->
                assertEquals(expectedError, err1)
                assertEquals(expectedResult, res1)
            }
        }
    }
}