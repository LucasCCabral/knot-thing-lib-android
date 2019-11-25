package com.example.knot_thing

import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingAuth
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingRegister
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingSchema
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingUnregister
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingUpdateData
import com.google.gson.Gson

class KNoTAMQPController(val kNoTAMQP: KNoTAMQP) : KNoTMessageProtocol {

    override fun register( knotThingRegister : KNoTThingRegister) {
        kNoTAMQP.publish(
            Gson().toJson(knotThingRegister),
            kNoTAMQP.EXCHANGE_NAME_FOG,
            kNoTAMQP.BINDING_KEY_REGISTER
        )
    }

    override fun unregister(kNoTThingUnregister: KNoTThingUnregister) {
        kNoTAMQP.publish(
            Gson().toJson(kNoTThingUnregister),
            kNoTAMQP.EXCHANGE_NAME_FOG,
            kNoTAMQP.BINDING_KEY_UNREGISTER
        )
    }

    override fun updateSchema(kNoTThingSchema: KNoTThingSchema) {
        kNoTAMQP.publish(
            Gson().toJson(kNoTThingSchema),
            kNoTAMQP.EXCHANGE_NAME_FOG,
            kNoTAMQP.BINDING_KEY_SCHEMA_UPDATE
        )
    }

    override fun authenticate(kNoTThingAuth: KNoTThingAuth) {
        kNoTAMQP.publish(
            Gson().toJson(kNoTThingAuth),
            kNoTAMQP.EXCHANGE_NAME_FOG,
            kNoTAMQP.BINDING_KEY_AUTHENTICATE
        )
    }

    override fun publishData(kNoTThingUpdateData: KNoTThingUpdateData) {
        kNoTAMQP.publish(
            Gson().toJson(kNoTThingUpdateData),
            kNoTAMQP.EXCHANGE_NAME_FOG,
            kNoTAMQP.BINDING_KEY_DATA_PUBLISH
        )
    }
}
