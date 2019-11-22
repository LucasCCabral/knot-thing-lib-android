package com.example.knot_thing_lib_android

import com.example.knot_thing_lib_android.KNoTControlMessages.*
import com.google.gson.Gson

class KNoTAMQPController( val kNoTAMQP: KNoTAMQP) {

    fun register( knotThingRegister : KNoTThingRegister) {
        kNoTAMQP.publish(
            Gson().toJson(knotThingRegister),
            kNoTAMQP.EXCHANGE_NAME_FOG,
            kNoTAMQP.BINDING_KEY_REGISTER
        )
    }

    fun unregister(kNoTThingUnregister: KNoTThingUnregister) {
        kNoTAMQP.publish(
            Gson().toJson(kNoTThingUnregister),
            kNoTAMQP.EXCHANGE_NAME_FOG,
            kNoTAMQP.BINDING_KEY_UNREGISTER
        )
    }

    fun updateSchema(kNoTThingSchema: KNoTThingSchema) {
        kNoTAMQP.publish(
            Gson().toJson(kNoTThingSchema),
            kNoTAMQP.EXCHANGE_NAME_FOG,
            kNoTAMQP.BINDING_KEY_SCHEMA_UPDATE
        )
    }

    fun authenticate(kNoTThingAuth: KNoTThingAuth) {
        kNoTAMQP.publish(
            Gson().toJson(kNoTThingAuth),
            kNoTAMQP.EXCHANGE_NAME_FOG,
            kNoTAMQP.BINDING_KEY_AUTHENTICATE
        )
    }

    fun publishData(kNoTThingUpdateData: KNoTThingUpdateData) {
        kNoTAMQP.publish(
            Gson().toJson(kNoTThingUpdateData),
            kNoTAMQP.EXCHANGE_NAME_FOG,
            kNoTAMQP.BINDING_KEY_DATA_PUBLISH
        )
    }
}
