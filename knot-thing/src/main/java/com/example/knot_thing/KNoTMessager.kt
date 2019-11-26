package com.example.knot_thing

import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingAuth
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingRegister
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingUpdateSchema
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingUnregister

interface KNoTMessager {

    fun register( knotThingRegister : KNoTThingRegister)

    fun unregister(kNoTThingUnregister: KNoTThingUnregister)

    fun authenticate(kNoTThingAuth: KNoTThingAuth)

    fun updateSchema(kNoTThingSchema: KNoTThingUpdateSchema)

}
