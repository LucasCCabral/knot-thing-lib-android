package com.cesar.knot_sdk.knot_data

class KNoTValueFloat (
    val sensorId : Int,
    val value : Float
) : KNoTValue<KNoTValueFloat> {

    override fun lesser(kNoTValue: KNoTValueFloat) = this.value < kNoTValue.value

    override fun greater(kNoTValue: KNoTValueFloat) = this.value > kNoTValue.value
}
