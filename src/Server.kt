import impl.MenuImpl

import java.rmi.Naming
import java.rmi.registry.LocateRegistry

object Server {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val port = 8000
            LocateRegistry.createRegistry(port)
            Naming.rebind("rmi://localhost:$port/menu", MenuImpl())
            println("server ready")
        } catch (e: Exception) {
            // TODO: handle exception
            println("echec : $e")
        }

    }
}
