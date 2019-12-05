package com.example.knot_thing

import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingAuth
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingRegister
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingUpdateSchema
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingUnregister
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingUpdateData

/**
 * This interface represents all messages that can be delivered with the KNoT protocol. Classes
 * that implement this interface can use any kind of technology to send these messages (zzzzzzzzAMQP,
 * WebSockets, etc), but should offer these operations.
 */
interface KNoTMessager {

    //These methods should wait for a json.
    // another interface should have the implementation for publishInt =
    //                                                      publishData(Gson().toJson(knotThingInt))
    /**
     * KNoTThingUpdateData
     */

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
