package com.cesar.knot_sdk.knot_data

/**
 * This class is an abstraction for the meta-data of it's associated KNoTValue. For instance
 * the value of an amperimeter could be in any unit (e.g.: Amperes, mili-amperes, ...).
 *
 * @property name the name of the sensor
 * @property typeID the dimension being analyzed (e.g.: temperature, velocity, light, ...)
 * @property unit represents the unit associated with this KNoTValue
 * @property valueType the value type (e.g.: int, string, boolean ...)
 */
data class KNoTSchema(
    val sensorId : Int,
    val valueType : Int,
    val unit : Int,
    val typeId : Int,
    val name : String
)
