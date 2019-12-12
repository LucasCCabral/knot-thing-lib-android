package com.cesar.knot_thing_lib_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cesar.knot_sdk.KNoTAMQP
import com.cesar.knot_sdk.KNoTAMQPFactory
import com.cesar.knot_sdk.KNoTMessager
import com.cesar.knot_sdk.KNoTTypes.KNOT_TYPE_ID_SWITCH
import com.cesar.knot_sdk.KNoTTypes.KNOT_UNIT_NOT_APPLICABLE
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_BOOL
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_INT
import com.cesar.knot_sdk.KNoTTypes.KNOT_VALUE_TYPE_RAW
import com.cesar.knot_sdk.knot_messages.KNoTSchema
import com.cesar.knot_sdk.knot_messages.KNoTThingAuth
import com.cesar.knot_sdk.knot_messages.KNoTThingData
import com.cesar.knot_sdk.knot_messages.KNoTThingRegister
import com.cesar.knot_sdk.knot_messages.KNoTThingUnregister
import com.cesar.knot_sdk.knot_messages.KNoTThingUpdateSchema
import com.example.knot_sdk.KNoTDataMessageParser
import kotlinx.android.synthetic.main.activity_main.register_button
import kotlinx.android.synthetic.main.activity_main.unregister_button
import kotlinx.android.synthetic.main.activity_main.authenticate_button
import kotlinx.android.synthetic.main.activity_main.schema_update_button
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {
    val HOSTNAME = "192.168.31.61"
    val PORT_NUMBER = 5672
    val USERNAME = "lucas"
    val PASSWORD = "lucas"
    val THING_ID = "a74151d19de59cd3"
    val THING_NAME = "pocophone-lucas"
    val THING_TOKEN = "ejfhwekhrui234huirh23uf"
    val SENSOR_ID = 1
    val SENSOR_NAME = "updateSchemaTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val kNoTAMQP = KNoTAMQP(USERNAME, PASSWORD, HOSTNAME, PORT_NUMBER)
        lateinit var kNoTMessager : KNoTMessager
        val setKNoTMessager = { kNoTMessagerAux : KNoTMessager -> kNoTMessager = kNoTMessagerAux }
        KNoTAMQPFactory().getKNoTProtocolMessager(kNoTAMQP, setKNoTMessager)
        val kNoTThingRegister = KNoTThingRegister(THING_ID, THING_NAME)
        val kNoTThingUnregister = KNoTThingUnregister(THING_ID)
        val kNoTThingAuth = KNoTThingAuth(THING_ID, THING_TOKEN)
        val kNoTThingSchema = mutableListOf(
            KNoTSchema(
                SENSOR_ID,
                KNOT_VALUE_TYPE_BOOL,
                KNOT_UNIT_NOT_APPLICABLE,
                KNOT_TYPE_ID_SWITCH,
                SENSOR_NAME
            )
        )

        val myjson = "{\n" +
                "  \"id\": \"fbe64efa6c7f717e\",\n" +
                "  \"data\": [{\n" +
                "      \"sensorId\": 1,\n" +
                "          \"value\": true\n" +
                "  },\n" +
                "  {\n" +
                "      \"sensorId\": 2,\n" +
                "          \"value\": \"this is a string\"\n" +
                "  },\n" +
                "{\n" +
                "      \"sensorId\": 3,\n" +
                "          \"value\": true\n" +
                "  },\n" +
                "{\n" +
                "      \"sensorId\": 4,\n" +
                "          \"value\": 57\n" +
                "  }\n" +
                "\n" +
                "]\n" +
                "}\n"

        var  kNoTDataMessageParser =  KNoTDataMessageParser()
        kNoTDataMessageParser.addSensor(2, KNOT_VALUE_TYPE_RAW)
        kNoTDataMessageParser.addSensor(3, KNOT_VALUE_TYPE_BOOL)
        kNoTDataMessageParser.addSensor(4, KNOT_VALUE_TYPE_INT)
        kNoTDataMessageParser.parseData(myjson)

        val kNoTThingUpdateSchema = KNoTThingUpdateSchema(THING_ID, kNoTThingSchema)

        val knotData = mutableListOf(
            KNoTThingData(SENSOR_ID, true),
            KNoTThingData(SENSOR_ID, false),
            KNoTThingData(SENSOR_ID, true)
        )


        register_button.setOnClickListener {
            doAsync { kNoTMessager.register(kNoTThingRegister) }
        }

        unregister_button.setOnClickListener {
            doAsync { kNoTMessager.unregister(kNoTThingUnregister) }
        }

        authenticate_button.setOnClickListener {
            doAsync { kNoTMessager.authenticate(kNoTThingAuth) }
        }

        schema_update_button.setOnClickListener {
            doAsync { kNoTMessager.updateSchema(kNoTThingUpdateSchema) }
        }

        /*
        data_publish_button.setOnClickListener {
            doAsync { kNoTMessager.publishData(kNoTThingUpdateData) }
        }
        */


    }
}
