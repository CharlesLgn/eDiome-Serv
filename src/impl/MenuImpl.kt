package impl

import inter.MenuInterface

import java.rmi.Naming
import java.rmi.RemoteException
import java.rmi.server.UnicastRemoteObject

class MenuImpl @Throws(RemoteException::class)
constructor() : UnicastRemoteObject(), MenuInterface {

    private var numServ: Int = 0

    @Throws(RemoteException::class)
    override
    fun createNewServer(): String {
        try {
            val port = 8000

            Naming.rebind("rmi://localhost:$port/serv$numServ", ServerImpl())
            return "$numServ"
        } catch (e: Exception) {
            return "$e"
        }
    }

    @Throws(RemoteException::class)
    override
    fun deleteServer(nbServ: Int) {
        try {
            val port = 8000

            Naming.unbind("rmi://localhost:$port/serv$nbServ")
        } catch (e: Exception) {
            println("echec : $e")
        }

    }
}
