package inter

import metier.Message

import java.rmi.Remote
import java.rmi.RemoteException
import java.util.ArrayList

interface ServerInterface : Remote {


    val messages: ArrayList<Message>
        @Throws(RemoteException::class) get(){
            return this.messages
        }


    @Throws(RemoteException::class)
    fun send(pseudo: String, message: String)
}
