package com.cesar.knot_sdk

import com.cesar.knot_sdk.knot_messages.KNoTThingRegister
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

}
