package com.example.knot_thing

import com.example.knot_thing_lib_android.KNoTAMQP
import com.example.knot_thing_lib_android.KNoTAMQPController
import org.jetbrains.anko.doAsync

class KNoTAMQPFactory() {

    fun getKNoTProtocolMessager(kNoTAMQP : KNoTAMQP, callback : (KNoTMessager) -> Unit ) {
        doAsync {
            kNoTAMQP.startConnection()
            kNoTAMQP.createExchange(kNoTAMQP.EXCHANGE_NAME_CLOUD)
            kNoTAMQP.createExchange(kNoTAMQP.EXCHANGE_NAME_FOG)
            kNoTAMQP.createQueue(kNoTAMQP.QUEUE_NAME_FOG_IN)
            kNoTAMQP.createQueue(kNoTAMQP.QUEUE_NAME_FOG_OUT)

            kNoTAMQP.bindQueue(kNoTAMQP.QUEUE_NAME_FOG_IN,
                kNoTAMQP.EXCHANGE_NAME_FOG,
                kNoTAMQP.BINDING_KEY_REGISTER)
        }
        callback(KNoTAMQPController(kNoTAMQP))
    }
}
