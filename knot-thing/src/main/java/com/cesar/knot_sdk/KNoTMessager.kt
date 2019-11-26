package com.cesar.knot_sdk

import com.cesar.knot_sdk.knot_messages.KNoTThingRegister
import com.cesar.knot_sdk.knot_messages.KNoTThingUnregister
import com.cesar.knot_sdk.knot_messages.KNoTThingAuth
import com.cesar.knot_sdk.knot_messages.KNoTThingUpdateData
import com.cesar.knot_sdk.knot_messages.KNoTThingUpdateSchema

/**
 * This interface represents all operations that are available with the KNoT
 * protocol.
 * Classes that implement this interface can use any kind of technology to send
 * these messages (AMQP, WebSockets, etc), but should support these operations.
 */
interface KNoTMessager {

    /**
     * Register a KNoT Thing in a KNoT Cloud.
     */
    fun register( knotThingRegister : KNoTThingRegister)

    /**
     * Unregister a KNoT Thing in a KNoT Cloud.
     */
    fun unregister(kNoTThingUnregister: KNoTThingUnregister)

    /**
     * Authenticates a KNoT Thing in a KNoT Cloud.
     */
    fun authenticate(kNoTThingAuth: KNoTThingAuth)

    /**
     * Updates the schema for a KNoT Thing in a KNoT Cloud.
     */
    fun updateSchema(kNoTThingSchema: KNoTThingUpdateSchema)

    /**
     * Publishes data from a KNoT Thing in a KNoT Cloud.
     */
    fun publishData(kNoTThingUpdateData: KNoTThingUpdateData)

}
