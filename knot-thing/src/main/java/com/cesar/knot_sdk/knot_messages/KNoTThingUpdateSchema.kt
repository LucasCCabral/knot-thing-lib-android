package com.cesar.knot_sdk.knot_messages

/**
 * This is the class that is serialized to update the sensors schema
 * @property id the Thing unique identifier
 * @property schema the Things schema
 */
data class KNoTThingUpdateSchema (
    val id : String,
    val schema : List<KNoTSchema>
)
