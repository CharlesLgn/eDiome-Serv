package impl

import inter.ServerInterface
import metier.Message

import java.rmi.RemoteException
import java.rmi.server.UnicastRemoteObject
import java.time.LocalDate
import java.util.ArrayList
import java.util.Arrays

class ServerImpl @Throws(RemoteException::class)
constructor() : UnicastRemoteObject(), ServerInterface {

    override
    val messages = ArrayList(Arrays.asList(Message("toto", LocalDate.now(), "tes1"),
                Message("toto", LocalDate.now(), "tes2")))

    @Throws(RemoteException::class)
    override
    fun send(pseudo: String, message: String) {
        this.messages.add(Message(pseudo, LocalDate.now(), message))
    }
}
