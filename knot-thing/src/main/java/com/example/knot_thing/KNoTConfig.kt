package com.example.knot_thing

data class KNoTConfig(
    val eventFlags : Int,
    val timeSec : Int,
    val lowerLimit : KNoTValue,
    val upperLimit : KNoTValue
)
