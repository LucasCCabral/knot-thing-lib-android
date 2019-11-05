package com.example.knot_thing

import com.google.gson.Gson

class KNoTValueInt(var value: Int) : KNoTSerializable, KNoTValue<KNoTValueInt> {

    override fun lesser(kNoTValue: KNoTValueInt): Boolean {
        if( this.value < kNoTValue.value)
            return true
        return false
    }

    override fun greater(kNoTValue: KNoTValueInt): Boolean {
        if( this.value > kNoTValue.value)
            return true
        return false
    }

    override fun serialize(): String {
        return Gson().toJson(this)
    }

    override fun deserialize(jsonString : String) {
        val gson = Gson()
        var knotValueInt = gson.fromJson(jsonString, KNoTValueInt::class.java)
        this.value = knotValueInt.value
    }

}
