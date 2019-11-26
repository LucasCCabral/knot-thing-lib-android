package com.cesar.knot_sdk.knot_messages

/**
 * This is the class that is serialized to update data values for all sensors.
 * @property id the Thing unique identifier
 * @property data a Pair of Int (sensorId) and Any (value).
 */
data class KNoTThingUpdateData(
    val id : String,
    val data : List<KNoTThingData>
)
