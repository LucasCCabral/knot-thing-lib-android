package com.cesar.knot_thing

import com.google.gson.Gson


/**
 * This interface is an abstraction for a objects serialization and desserialization
 */
class KNoTSerializable {

    /**
     * This method makes a serialization of the current class and returns the json string
     * equivalent.
     * @return
     */
    fun serialize() : String {
        return Gson().toJson(this)
    }

}