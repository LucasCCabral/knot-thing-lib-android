package com.cesar.knot_sdk.knot_data

class KNoTValueInt (
    val sensorId : Int,
    val value : Int
) : KNoTValue<KNoTValueInt> {

    override fun lesser(kNoTValue: KNoTValueInt) = this.value < kNoTValue.value

    override fun greater(kNoTValue: KNoTValueInt) = this.value > kNoTValue.value

    override fun toString() = "SensorID: " + sensorId + "\nValue: " + value

}
