package com.example.knot_thing_lib_android

import com.example.knot_thing.KNoTControlMessages.KNoTThingRegistered
import com.google.gson.Gson
import com.rabbitmq.client.*
import java.io.IOException


class KNoTAMQP(username : String, password : String, hostname : String, port : Int) {

    val EXCHANGE_TYPE_TOPIC = "topic"

    val EXCHANGE_NAME_CLOUD = "cloud"
    val EXCHANGE_NAME_FOG = "fog"

    val BINDING_KEY_REGISTER = "device.register"
    val BINDING_KEY_REGISTERED = "device.registered"
    val BINDING_KEY_UNREGISTER = "device.unregister"
    val BINDING_KEY_AUTHENTICATE = "device.cmd.auth"
    val BINDING_KEY_SCHEMA_UPDATE = "schema.update"
    val BINDING_KEY_DATA_PUBLISH = "data.publish"

    val QUEUE_NAME_FOG_IN = "fogin"
    val QUEUE_NAME_FOG_OUT = "fogout"

    val CONSUMER_NAME = "KNoTThingConsumer"


    val factory = ConnectionFactory()

    lateinit var conn : Connection
    lateinit var channel : Channel

    init {
        factory.host = hostname
        factory.username = username
        factory.password = password
        factory.port = port
    }

    fun startConnection() {
        conn = factory.newConnection()
        channel = conn.createChannel()
    }

    fun createExchange(
        exchangeName : String,
        exchangeType : String = EXCHANGE_TYPE_TOPIC) {

        channel.exchangeDeclare(
            exchangeName,
            exchangeType,
            true
        )
    }

    fun createQueue(queueName: String) {
        channel.queueDeclare(
            queueName,
            true,
            false,
            false,
            null
        ).queue
    }

    fun bindQueue( queueName : String, exchangeName : String, routingKey: String) {
        channel.queueBind(queueName, exchangeName, routingKey)
    }

    fun publish(message : String, exchange : String, routingKey : String) {
        val messageBodyBytes = message.toByteArray()
        val messageProperties = AMQP.BasicProperties.Builder()
            .deliveryMode(2)
            .build()

        channel.basicPublish(
            exchange,
            routingKey,
            messageProperties,
            messageBodyBytes
        )
    }

    fun createConsumer(queueName : String, consumerTag : String) {
        val autoAck = false
        channel.basicConsume(queueName, autoAck, consumerTag,
            object : DefaultConsumer(channel) {
                @Throws(IOException::class)
                override fun handleDelivery(
                    consumerTag: String,
                    envelope: Envelope,
                    properties: AMQP.BasicProperties,
                    body: ByteArray
                ) {
                    val deliveryTag = envelope.getDeliveryTag()
                    val bodyJson = String(body)
                    val kNoTThingRegistered = Gson().fromJson(bodyJson,
                        KNoTThingRegistered::class.java
                    )

                    channel.basicAck(deliveryTag, false)
                }
            })
    }

    fun disconnect() {
            channel.close()
            conn.close()
    }

}
