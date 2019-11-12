package com.example.knot_thing

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import org.jetbrains.anko.doAsync

class KNoTAMQP(username : String, password : String, hostname : String, port : Int) {

    val QUEUE_AUTO_GEN_NAME = ""

    val EXCHANGE_TYPE_TOPIC = "topic"
    val EXCHANGE_KEY_CLOUD = "cloud"

    val BINDING_KEY_REGISTER = "device.register"
    val BINDING_KEY_SCHEMA = "schema.update"
    val BINDING_KEY_AUTHENTICATE = "device.cmd.auth"

    val factory = ConnectionFactory()
    lateinit var conn : Connection
    lateinit var channel : Channel

    init {
        factory.host = hostname
        factory.username = username
        factory.password = password
        factory.port = port
    }

    fun connect() {
        doAsync {
            conn = factory.newConnection()
            channel = conn.createChannel()
        }
    }

    fun createQueue() {
        doAsync {
            channel.exchangeDeclare(EXCHANGE_KEY_CLOUD, EXCHANGE_TYPE_TOPIC, true)
            val queueName = channel.queueDeclare(QUEUE_AUTO_GEN_NAME,
                true,
                false,
                false,
                null
            ).queue
            channel.queueBind(queueName, EXCHANGE_KEY_CLOUD, BINDING_KEY_REGISTER)
        }
    }

    fun disconnect() {
        doAsync {
            channel.close()
            conn.close()
        }
    }

}

