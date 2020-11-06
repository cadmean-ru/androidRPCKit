package ru.cadmean.androidrpckit

import kotlinx.serialization.Serializable

@Serializable
public data class JwtAuthorizationTicket (
    public var accessToken: String = "",
    public var refreshToken: String = "",
)