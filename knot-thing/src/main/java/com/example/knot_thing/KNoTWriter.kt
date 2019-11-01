package com.example.knot_thing

/**
 * This interface works as an abstraction to the write operation on KNoT devices. This means that
 * devices that sensors that implement this interface act as actuators, which means, it can have
 * values written into it.
 * e.g.: a flashlight can be told to turn on/off.
 */
interface KNoTWriter<T> {
    /**
     * This is the method that is going to be used to write a value into an actuator. The expected
     * KNoTValue should be of the same types as the object that implements this interface.
     * i.e.: a flashlight expects only flashlight values to be written into it.
     */
    fun write(value : T)
}
