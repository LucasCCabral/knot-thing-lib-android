package com.example.knot_thing.KNoTSensor

class KNoTValueInt (
    val sensorId : Int,
    val value : Int
) : KNoTValue<KNoTValueInt> {

    override fun lesser(kNoTValue: KNoTValueInt) = this.value < kNoTValue.value

    override fun greater(kNoTValue: KNoTValueInt) = this.value > kNoTValue.value

    override fun toString() = "SensorID: " + sensorId + "\nValue: " + value

}
