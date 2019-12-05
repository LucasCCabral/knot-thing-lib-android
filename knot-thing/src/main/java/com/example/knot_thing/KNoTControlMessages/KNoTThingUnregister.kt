package com.example.knot_thing_lib_android.KNoTControlMessages

/**
 * This is the class that is serialized as an unregister message.
 * @property id the Thing unique identifier
 */
data class KNoTThingUnregister(
    val id : String
)
