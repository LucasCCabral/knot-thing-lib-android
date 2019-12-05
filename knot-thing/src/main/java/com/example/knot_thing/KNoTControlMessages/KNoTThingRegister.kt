package com.example.knot_thing_lib_android.KNoTControlMessages

/**
 * This is the class that is serialized for the authetication message.
 * @property id the Thing unique identifier
 * @property name the name for the Thing
 */
data class KNoTThingRegister(
    val id : String,
    val name : String
)
