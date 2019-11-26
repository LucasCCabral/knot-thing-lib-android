package com.cesar.knot_sdk

import com.cesar.knot_sdk.knot_messages.KNoTThingAuth
import com.cesar.knot_sdk.knot_messages.KNoTThingRegister
import com.cesar.knot_sdk.knot_messages.KNoTThingUnregister
import com.cesar.knot_sdk.knot_messages.KNoTThingUpdateData
import com.cesar.knot_sdk.knot_messages.KNoTThingUpdateSchema
import com.google.gson.Gson

/**
 * This class implements the KNoT-Protocol operations for AMQP.
 */
class KNoTAMQPController( val kNoTAMQP: KNoTAMQP) : KNoTMessager {

    override fun register( knotThingRegister : KNoTThingRegister) {
        kNoTAMQP.publish(
            Gson().toJson(knotThingRegister),
            kNoTAMQP.EXCHANGE_NAME_CLOUD,
            kNoTAMQP.BINDING_KEY_REGISTER
        )
    }

    override fun unregister(kNoTThingUnregister: KNoTThingUnregister) {
        kNoTAMQP.publish(
            Gson().toJson(kNoTThingUnregister),
            kNoTAMQP.EXCHANGE_NAME_CLOUD,
            kNoTAMQP.BINDING_KEY_UNREGISTER
        )
    }

    override fun authenticate(kNoTThingAuth: KNoTThingAuth) {
        kNoTAMQP.publish(
            Gson().toJson(kNoTThingAuth),
            kNoTAMQP.EXCHANGE_NAME_CLOUD,
            kNoTAMQP.BINDING_KEY_AUTHENTICATE
        )
    }

    override fun updateSchema(kNoTThingSchema: KNoTThingUpdateSchema) {
        kNoTAMQP.publish(
            Gson().toJson(kNoTThingSchema),
            kNoTAMQP.EXCHANGE_NAME_CLOUD,
            kNoTAMQP.BINDING_KEY_SCHEMA_UPDATE
        )
    }

    override fun publishData(kNoTThingUpdateData: KNoTThingUpdateData) {
        kNoTAMQP.publish(
            Gson().toJson(kNoTThingUpdateData),
            kNoTAMQP.EXCHANGE_NAME_CLOUD,
            kNoTAMQP.BINDING_KEY_DATA_PUBLISH
        )
    }

}
