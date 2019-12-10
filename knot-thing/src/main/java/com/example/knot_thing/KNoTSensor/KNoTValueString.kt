package com.example.knot_thing.KNoTSensor

class KNoTValueString (
    val id : Int,
    val value : String
) : KNoTValue<KNoTValueString> {

    override fun lesser(kNoTValue: KNoTValueString) = this.value.length < kNoTValue.value.length

    override fun greater(kNoTValue: KNoTValueString) = this.value.length > kNoTValue.value.length

}