package com.cesar.knot_sdk

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory


/**
 * This class is an abstraction over the rabbitMQ java library it handles all
 * AMQP specific operations, such as creation of connection, queues and
 * consumers.
 * This class contains all KNoT-Protocol specific keywords, such as, the binding
 * keys and queue/consumer names.
 * @param username the rabbitMQ username in the connector
 * @param password the rabbitMQ password in the connector
 * @param hostname the ip of the server running the connector
 * @param port the port that rabbitMQ will be listening to (by default 5672)
 */
class KNoTAMQP(username : String, password : String, hostname : String, port : Int) {

    val EXCHANGE_TYPE_TOPIC = "topic"

    val EXCHANGE_NAME_CLOUD = "cloud"
    val EXCHANGE_NAME_FOG = "fog"

    val BINDING_KEY_REGISTER = "device.register"
    val BINDING_KEY_UNREGISTER = "device.unregister"
    val BINDING_KEY_AUTHENTICATE = "device.cmd.auth"

    val QUEUE_NAME_FOG_IN = "fogIn"
    val QUEUE_NAME_FOG_OUT = "fogOut"

    val factory = ConnectionFactory()

    lateinit var conn : Connection
    lateinit var channel : Channel

    init {
        factory.host = hostname
        factory.username = username
        factory.password = password
        factory.port = port
    }

    /**
     * Starts the connection with the rabbitMQ server. Uses the parameters
     * defined in factory. Should not be executed in the main thread as it
     * performs network operations.
     */
    fun startConnection() {
        conn = factory.newConnection()
        channel = conn.createChannel()
    }

    /**
     * Creates an durable exchange with the name and type given in the
     * parametes.
     * @param exchangeName the name of the exchange
     * @param exchangeType the type of the exchange
     */
    fun createExchange(
        exchangeName : String,
        exchangeType : String = EXCHANGE_TYPE_TOPIC) {

        channel.exchangeDeclare(
            exchangeName,
            exchangeType,
            true
        )
    }

    /**
     * Creates a durable, non-exclusive and non self-deleting queue without
     * extra parameters with the given name.
     * @param queueName the name of the queue, must be either fogIn or fogOut.
     */
    fun createQueue(queueName: String) {
        channel.queueDeclare(
            queueName,
            true,
            false,
            false,
            null
        ).queue
    }

    /**
     * Binds a queue to an exchange and a routing key. Both the queue and the
     * exchange should already exist in the channel.
     * @param queueName name of an existing queue
     * @param exchangeName name of an existing exchange
     * @param routingKey the name of the desired queue
     */
    fun bindQueue( queueName : String, exchangeName : String, routingKey: String) {
        channel.queueBind(queueName, exchangeName, routingKey)
    }

    /**
     * Publishes a message, in the specified exchange with the specified routing
     * key. The message being sent, should respect the KNoT Protocol, which
     * means it should be a formatted JSON and the routing key and exchange
     * should be the proper ones.
     * @param message the message that is going to be sent
     * @param exchange the exchange that will hold the message
     * @param routingKey the routing key the message is associated with
     */
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

    /**
     * This method is responsible for releasing the resources used by the AMQP
     * communication. This method should only be called when ending a connection
     * (when there is no intention of publishing/consuming data). Ideally this
     * method should only be called when the application is being destroyed.
     */
    fun disconnect() {
            channel.close()
            conn.close()
    }

}
