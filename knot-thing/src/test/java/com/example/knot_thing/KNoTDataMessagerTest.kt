package com.example.knot_thing

import com.example.knot_thing.KNoTTypes.KNOT_VALUE_TYPE_INT
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.junit.Assert.assertEquals
import org.junit.Test


class KNoTDataMessagerTest {

    val SENSOR_ID_1 = 1
    val SENSOR_TYPE = KNOT_VALUE_TYPE_INT
    val VALUE = 12

    @Test
    fun getSensorId_isCorrect() {
        val kNoTDataMessageParser = KNoTDataMessageParser()
        kNoTDataMessageParser.addSensor(SENSOR_ID_1, SENSOR_TYPE)
        val sensorData
                = "{\"sensorId\": " + SENSOR_ID_1 + "," +
                   "\"value\": "    + VALUE       + "}"

        val jsonElement = JsonParser.parseString(sensorData).asJsonObject

        val getSensorId = KNoTDataMessageParser::class.java.getDeclaredMethod(
            "getSensorId",
            JsonElement::class.java
        )
        getSensorId.isAccessible = true
        val id = getSensorId.invoke(kNoTDataMessageParser, jsonElement)


        assertEquals(SENSOR_ID_1, id)
    }

    @Test
    fun getSensorType_isSuccessful() {
        val kNoTDataMessageParser = KNoTDataMessageParser()
        kNoTDataMessageParser.addSensor(SENSOR_ID_1, SENSOR_TYPE)

        val getSensorType = KNoTDataMessageParser::class.java.getDeclaredMethod(
            "getSensorType",
            Int::class.java
        )
        getSensorType.isAccessible = true
        val type = getSensorType.invoke(kNoTDataMessageParser, SENSOR_ID_1)
        assertEquals(SENSOR_TYPE, type)
    }

}