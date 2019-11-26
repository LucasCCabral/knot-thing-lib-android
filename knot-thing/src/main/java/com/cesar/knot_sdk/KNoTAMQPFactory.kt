package com.cesar.knot_sdk

import org.jetbrains.anko.doAsync

/**
 * The creation of the KNoTAMQP class depends on a series of complicated
 * operations, some of which involve network operations. This class is
 * responsible solely to ease the creation of KNoTAMQP instances.
 */
class KNoTAMQPFactory() {

    /**
     * Configures the KNoT-AMQP instance.
     * @param kNoTAMQP a non-configured instance of the KNoTAMQP class.
     * @param callback a callback to return after the operations are done.
     */
    fun getKNoTProtocolMessager(
        kNoTAMQP : KNoTAMQP,
        callback : (KNoTMessager) -> Unit ) {
            doAsync {
                kNoTAMQP.startConnection()
                kNoTAMQP.createExchange(kNoTAMQP.EXCHANGE_NAME_CLOUD)
                kNoTAMQP.createExchange(kNoTAMQP.EXCHANGE_NAME_FOG)
                kNoTAMQP.createQueue(kNoTAMQP.QUEUE_NAME_FOG_IN)
                kNoTAMQP.createQueue(kNoTAMQP.QUEUE_NAME_FOG_OUT)

            kNoTAMQP.bindQueue(kNoTAMQP.QUEUE_NAME_FOG_IN,
                kNoTAMQP.EXCHANGE_NAME_FOG,
                kNoTAMQP.BINDING_KEY_REGISTER)

            kNoTAMQP.bindQueue(kNoTAMQP.QUEUE_NAME_FOG_IN,
                kNoTAMQP.EXCHANGE_NAME_FOG,
                kNoTAMQP.BINDING_KEY_UNREGISTER)

        }
        callback(KNoTAMQPController(kNoTAMQP))
    }
}
