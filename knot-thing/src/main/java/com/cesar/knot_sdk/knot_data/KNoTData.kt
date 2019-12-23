package com.cesar.knot_sdk.knot_data

/**
 * This class is an abstraction over a sensor. The schema defines metadata about
 * the sensor, the config defines how often the sensor updates and the value
 * defines the current value of the sensor.
 * @property schema the schema containing the sensor metadata
 * @property config the config with the flags/limits of the sensor value
 * @property value the current value of the sensor
 */
class KNoTData <T> (
    schema : KNoTSchema,
    config : KNoTConfig<T>,
    value : T
)
