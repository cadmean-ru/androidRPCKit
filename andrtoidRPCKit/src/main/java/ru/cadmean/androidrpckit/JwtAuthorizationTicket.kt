package ru.cadmean.androidrpckit

public data class JwtAuthorizationTicket (
    public var accessToken: String = "",
    public var refreshToken: String = "",
)