package com.example.knot_thing

class KNoTMessager {

    val KNOT_MESSAGE_DATA_FIELD = "data";

    /**
     * Receives a KNoTValue and transforms it into a KNoTMessage.
     *
     * @param null
     */
    fun mount(kNoTValue : KNoTSerializable) : String {
        return kNoTValue.serialize()
    }

    /**
     * Receives a KNoTMessage and returns a KNoTValue as JSON.
     *
     * @return32
     */
    fun umount(kNoTMessage : String, kNoTValue: KNoTSerializable) {
        kNoTValue.deserialize(kNoTMessage)
    }
}
