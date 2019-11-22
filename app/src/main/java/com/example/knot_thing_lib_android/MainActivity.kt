package com.example.knot_thing_lib_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingAuth
import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTThingUpdateData
import com.google.gson.Gson
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {
    val HOSTNAME = "192.168.31.61"
    val PORT_NUMBER = 5672

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val knotAuth = KNoTThingAuth("lucas", "dojhf2uohr3ejk13ejk123h12w123nl123n12j3k2")
        val kNoTAMQP = KNoTAMQP("lucas", "lucas", HOSTNAME, PORT_NUMBER)
        val kNoTAMQPController = KNoTAMQPController(kNoTAMQP)
        doAsync {
            kNoTAMQP.init()
            kNoTAMQPController.authenticate(knotAuth)
            //kNoTAMQP.disconnect()
        }
    }
}
