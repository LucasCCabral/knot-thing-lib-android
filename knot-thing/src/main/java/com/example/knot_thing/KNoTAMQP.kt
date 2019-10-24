package com.example.knot_thing_lib_android

import com.rabbitmq.client.*
import java.io.IOException


class KNoTAMQP(username : String, password : String, hostname : String, port : Int) {

    val EXCHANGE_TYPE_TOPIC = "topic"

    val EXCHANGE_NAME_CLOUD = "cloud"
    val EXCHANGE_NAME_FOG = "fog"

    val BINDING_KEY_REGISTER = "device.register"

    val QUEUE_NAME_FOG_IN = "fogin"
    val QUEUE_NAME_FOG_OUT = "fogout"

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

    fun disconnect() {
            channel.close()
            conn.close()
    }

}
