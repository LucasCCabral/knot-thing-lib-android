package com.cesar.knot_sdk.knot_messages

/**
 * This is the class that is serialized for one data value, from one sensor.
 * @property id the Thing unique identifier
 * @property data the data batch that is to be deployed
 */
data class KNoTThingData(
    val sensorId : Int,
    val value : Boolean
)
