package com.cesar.knot_sdk.knot_messages

/**
 * This is the class that is serialized as an authentication message.
 * @property id the Thing unique identifier
 * @property token the authentication token provided after registration
 */
data class KNoTThingAuth(
    val id : String,
    val token : String
)
