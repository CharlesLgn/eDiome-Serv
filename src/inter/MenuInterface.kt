package inter

import java.rmi.Remote
import java.rmi.RemoteException

interface MenuInterface : Remote {

    @Throws(RemoteException::class)
    fun createNewServer(): String

    @Throws(RemoteException::class)
    fun deleteServer(nbServ: Int)
}
