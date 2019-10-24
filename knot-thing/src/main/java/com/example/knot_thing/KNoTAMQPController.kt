package com.example.knot_thing_lib_android

import com.example.knot_thing.KNoTMessager
import com.example.knot_thing_lib_android.KNoTControlMessages.*
import com.google.gson.Gson

class KNoTAMQPController( val kNoTAMQP: KNoTAMQP) : KNoTMessager {

    override fun register( knotThingRegister : KNoTThingRegister) {
        kNoTAMQP.publish(
            Gson().toJson(knotThingRegister),
            kNoTAMQP.EXCHANGE_NAME_FOG,
            kNoTAMQP.BINDING_KEY_REGISTER
        )
    }

}
