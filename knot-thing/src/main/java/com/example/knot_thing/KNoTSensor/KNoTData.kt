package com.example.knot_thing.KNoTSensor

import com.example.knot_thing_lib_android.KNoTControlMessages.KNoTSchema

class KNoTData <T> (
    schema : KNoTSchema,
    config : KNoTConfig<T>,
    value : T
)