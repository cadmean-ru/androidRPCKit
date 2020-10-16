package ru.cadmean.androidrpckit

public class TransientAuthorizationTicketHolder : AuthorizationTicketHolder {
    override var ticket: JwtAuthorizationTicket? = null
}