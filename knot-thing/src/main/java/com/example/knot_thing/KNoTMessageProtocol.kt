package com.example.knot_thing

import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingAuth
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingRegister
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingSchema
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingUnregister
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingUpdateData

interface KNoTMessageProtocol {

    fun register( knotThingRegister : KNoTThingRegister)

    fun unregister(kNoTThingUnregister: KNoTThingUnregister)

    fun updateSchema(kNoTThingSchema: KNoTThingSchema)

    fun authenticate(kNoTThingAuth: KNoTThingAuth)

    fun publishData(kNoTThingUpdateData: KNoTThingUpdateData)
}