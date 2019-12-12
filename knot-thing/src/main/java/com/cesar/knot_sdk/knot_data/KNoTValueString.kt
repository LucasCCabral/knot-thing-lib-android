package com.cesar.knot_sdk.knot_data

class KNoTValueString (
    val sensorID : Int,
    val value : String
) : KNoTValue<KNoTValueString> {

    override fun lesser(kNoTValue: KNoTValueString) = this.value.length < kNoTValue.value.length

    override fun greater(kNoTValue: KNoTValueString) = this.value.length > kNoTValue.value.length

    override fun toString() = "SensorID: " + sensorID + "\nValue: " + value

}