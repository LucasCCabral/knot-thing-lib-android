package com.example.knot_thing

import android.util.Log
import com.example.knot_thing.KNoTSensor.*
import com.example.knot_thing.KNoTTypes.KNOT_VALUE_NULL
import com.example.knot_thing.KNoTTypes.KNOT_VALUE_TYPE_BOOL
import com.example.knot_thing.KNoTTypes.KNOT_VALUE_TYPE_FLOAT
import com.example.knot_thing.KNoTTypes.KNOT_VALUE_TYPE_INT
import com.example.knot_thing.KNoTTypes.KNOT_VALUE_TYPE_RAW
import com.google.gson.JsonElement
import com.google.gson.JsonParser

class KNoTDataMessageParser {

    //first string is the sensor id, second is the type
    private val sensorHash = mutableMapOf<Int, Int>()
    private val VALUE = "value"
    private val DATA = "data"
    private val SENSOR_ID = "sensorId"
    private val ILLEGAL_ARGUMENT_MESSAGE = "The KNoTDataMessageParser is only prepared to handle " +
            "KNOT_VALUE_TYPE_INT, KNOT_VALUE_TYPE_FLOAT, KNOT_VALUE_TYPE_BOOL, " +
            "KNOT_VALUE_TYPE_RAW, KNOT_VALUE_NULL."

    fun parseData(knotData : String) : List<Any> {
        val jelement : JsonElement = JsonParser().parse(knotData)
        var jobject = jelement.asJsonObject
        val jarray = jobject.getAsJsonArray(DATA)
        val data = mutableListOf<Any>()
        var type : Int
        var id : Int
        jarray.forEach {
            Log.d("DEV-LOG", it.toString())
            id = getSensorId(it)
            type = getSensorType(id)
            when(type) {
                KNOT_VALUE_TYPE_INT -> {
                    data.add(KNoTValueInt(id, it.asJsonObject.get(VALUE).asInt))
                }
                KNOT_VALUE_TYPE_FLOAT -> {
                    data.add(KNoTValueFloat(id, it.asJsonObject.get(VALUE).asFloat))
                }
                KNOT_VALUE_TYPE_BOOL -> {
                    data.add(KNoTValueBool(id, it.asJsonObject.get(VALUE).asBoolean))
                }
                KNOT_VALUE_TYPE_RAW -> {
                    data.add(KNoTValueString(id, it.asJsonObject.get(VALUE).asString))
                }
                KNOT_VALUE_NULL -> { }
            }
        }

        return data
    }

    fun addSensor(key : Int, value : Int) {
        when(value) {
            KNOT_VALUE_TYPE_INT -> { }
            KNOT_VALUE_TYPE_FLOAT -> { }
            KNOT_VALUE_TYPE_BOOL -> { }
            KNOT_VALUE_TYPE_RAW -> { }
            KNOT_VALUE_NULL -> { }
            else -> {throw IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE)}
        }
        sensorHash[key] = value
    }

    private fun getSensorId(knotDataElement: JsonElement)
            = knotDataElement.asJsonObject.get(SENSOR_ID).asInt

    private fun getSensorType(sensorId : Int) : Int {
        val sensorType = sensorHash.get(sensorId)

        if(sensorType != null)
            return sensorType
        else
            Log.d("DEV-LOG", "This sensor has not been registered.")

        return KNOT_VALUE_NULL
    }

}