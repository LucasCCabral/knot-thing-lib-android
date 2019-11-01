package com.example.knot_thing

/**
 * This interface works as an abstraction to the read operation on KNoT devices. This means that
 * devices that implement this interface act as sensors whose values can be read.
 * e.g.: an accelerometer can have it's values read.
 */
interface KNoTReader<T> {
    /**
     * This is the method that is going to be used to read a value from a sensor. The expected
     * KNoTValue should be of the same type as the object that implements this interface.
     * i.e.: an accelerometer read should return an accelerometer data type.
     */
    fun read() : T
}
