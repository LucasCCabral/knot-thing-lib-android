package com.example.knot_thing

/**
 * This interface is an abstraction for a objects serialization and desserialization
 */
interface KNoTSerializable {

    /**
     * This method makes a serialization of the current class and returns the json string
     * equivalent.
     * @return
     */
    fun serialize() : String

    /**
     * This method is responsible for the desserialization of a json string, i.e. it takes a json
     * string and replace the values inside the object.
     * @param jsonstring
     */
    fun deserialize (jsonstring : String)
}
