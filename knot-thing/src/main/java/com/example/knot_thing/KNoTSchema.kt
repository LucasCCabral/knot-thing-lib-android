package com.example.knot_thing


/**
 * This class is an abstraction for the meta-data of it's associated KNoTValue. For instance
 * the value of an amperimeter could be in any unit (e.g.: Amperes, mili-amperes, ...).
 *
 * @property name the name of the sensor
 * @property typeID the dimension being analyzed (e.g.: temperature, velocity, light, ...)
 * @property unit represents the unit associated with this KNoTValue
 * @property valueType the value type
 */
data class KNoTSchema(
    val name : String,
    val typeID : Int,
    val unit : Int,
    val valueType : Int
)
