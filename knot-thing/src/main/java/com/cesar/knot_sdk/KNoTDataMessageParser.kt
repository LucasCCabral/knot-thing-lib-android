package com.cesar.knot_thing

import android.util.Log
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_NULL
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_BOOL
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_FLOAT
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_INT
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_RAW
import com.cesar.knot_sdk.knot_data.*
import com.google.gson.JsonElement
import com.google.gson.JsonParser

class KNoTDataMessageParser {

    //first string is the sensor id, second is the type
    val sensorHash = mutableMapOf<Int, Int>()

    fun parseData(knotData : String) {
        val jelement : JsonElement = JsonParser().parse(knotData)
        var jobject = jelement.asJsonObject
        val jarray = jobject.getAsJsonArray("data")
        val data = mutableListOf<Any>()
        var type : Int
        var id : Int
        jarray.forEach {
            id = getSensorId(it)
            type = getSensorType(it, id)
            when(type) {
                KNOT_VALUE_TYPE_INT -> {
                    data.add(KNoTValueInt(id, it.asJsonObject.get("sensorId").asInt))
                }
                KNOT_VALUE_TYPE_FLOAT -> {
                    data.add(KNoTValueFloat(id, it.asJsonObject.get("sensorId").asFloat))
                }
                KNOT_VALUE_TYPE_BOOL -> {
                    data.add(KNoTValueBool(id, it.asJsonObject.get("sensorId").asBoolean))
                }
                KNOT_VALUE_TYPE_RAW -> {
                    data.add(KNoTValueString(id, it.asJsonObject.get("sensorId").asString))
                }
                KNOT_VALUE_NULL -> { }
            }
        }

        data.forEach{
            Log.d("DEV-LOG", it.toString())
        }

    }

    fun addSensor(key : Int, value : Int) = sensorHash.put(key, value)

    private fun getSensorId(knotDataElement: JsonElement) : Int {
        val sensorId = knotDataElement.asJsonObject.get("sensorId").asInt
        return sensorId
    }

    private fun getSensorType(knotDataElement: JsonElement, sensorId : Int) : Int {
        val sensorType = sensorHash.get(sensorId)

        if(sensorType != null) {
            return sensorType
        } else {
            Log.d("DEV-LOG", "This sensor has not been registered.")
        }

        return KNOT_VALUE_NULL
    }

}
