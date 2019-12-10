package com.example.knot_thing_lib_android.KNoTControlMessages

/**
 * This is the class that is serialized to update the sensors schema
 * @property id the Thing unique identifier
 * @property schema the Things schema
 */
data class KNoTThingUpdateSchema (
    val id : String,
    val schema : List<KNoTSchema>
)
