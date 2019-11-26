package com.example.knot_thing

import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingRegister
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingUnregister

interface KNoTMessager {

    fun register( knotThingRegister : KNoTThingRegister)

    fun unregister(kNoTThingUnregister: KNoTThingUnregister)

}
