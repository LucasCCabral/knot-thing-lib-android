package com.cesar.knot_sdk.knot_data

class KNoTValueBool (
    val sensorId : Int,
    val value : Boolean
) : KNoTValue<KNoTValueBool> {

    override fun lesser(kNoTValue: KNoTValueBool) = this.value && kNoTValue.value

    override fun greater(kNoTValue: KNoTValueBool) = this.value || kNoTValue.value
}
