package com.cesar.knot_sdk.knot_messages

/**
 * This class is an abstraction for the registration message. Its property names
 * are already mapped to the expected values in the message json, which means
 * it's really easy to convert these values to the expected json message.
 * @property id a unique identifier for the Thing, be careful to not generate a
 * duplicate name, as the KNoT-Protocol does not define what happens when
 * receiving a duplicate id.
 * @property name a string that represents this device in some manner.
 */
data class KNoTThingRegister(
    val id : String,
    val name : String
)
