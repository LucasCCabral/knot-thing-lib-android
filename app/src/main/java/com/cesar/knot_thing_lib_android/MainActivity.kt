package com.cesar.knot_thing_lib_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cesar.knot_sdk.KNoTAMQP
import com.cesar.knot_sdk.KNoTAMQPFactory
import com.cesar.knot_sdk.KNoTMessager
import com.cesar.knot_sdk.knot_messages.KNoTThingAuth
import com.cesar.knot_sdk.knot_messages.KNoTThingRegister
import com.cesar.knot_sdk.knot_messages.KNoTThingUnregister
import kotlinx.android.synthetic.main.activity_main.register_button
import kotlinx.android.synthetic.main.activity_main.unregister_button
import kotlinx.android.synthetic.main.activity_main.authenticate_button
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {
    val HOSTNAME = "192.168.31.61"
    val PORT_NUMBER = 5672
    val USERNAME = "lucas"
    val PASSWORD = "lucas"
    val THING_ID = "a74151d19de59cd3"
    val THING_NAME = "pocophone-lucas"
    val THING_TOKEN = "ejfhwekhrui234huirh23uf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val kNoTAMQP = KNoTAMQP(USERNAME, PASSWORD, HOSTNAME, PORT_NUMBER)
        lateinit var kNoTMessager : KNoTMessager
        val setKNoTMessager = { kNoTMessagerAux : KNoTMessager -> kNoTMessager = kNoTMessagerAux }
        KNoTAMQPFactory().getKNoTProtocolMessager(kNoTAMQP, setKNoTMessager)
        val kNoTThingRegister = KNoTThingRegister(THING_ID, THING_NAME)
        val kNoTThingUnregister = KNoTThingUnregister(THING_ID)
        val kNoTThingAuth = KNoTThingAuth(THING_ID, "ejfhwekhrui234huirh23uf")

        register_button.setOnClickListener {
            doAsync { kNoTMessager.register(kNoTThingRegister) }
        }

        unregister_button.setOnClickListener {
            doAsync { kNoTMessager.unregister(kNoTThingUnregister) }
        }

        authenticate_button.setOnClickListener {
            doAsync { kNoTMessager.authenticate(kNoTThingAuth) }
        }

    }
}
