package com.example.knot_thing

/**
 * This class holds the definitions of periodicity and of upper and lower limits for
 * the associated KNoTValue.
 *
 * @property eventFlags theses are the flags that
 * @property timeSec this is a timeout for the update of the KNoTValue
 * @property lowerLimit this is the inferior limit of acceptable KNoTValues
 * @property upperLimit this is the superior limit of acceptable KNoTValues
 */
data class KNoTConfig<T>(
    val eventFlags : Int,
    val timeSec : Int,
    val lowerLimit : T,
    val upperLimit : T
)
