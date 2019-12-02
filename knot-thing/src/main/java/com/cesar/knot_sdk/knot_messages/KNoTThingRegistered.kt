package com.cesar.knot_sdk.knot_messages

/**
 * This is the class that deserialize the response of the registration message.
 * @property id the Thing unique identifier
 * @property token the token received after registration
 */
data class KNoTThingRegistered (
    val id : String,
    val token : String
)
