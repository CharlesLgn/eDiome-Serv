package test

import inter.MenuInterface
import java.rmi.Naming

object TestMenu {

    @JvmStatic
    fun main(args: Array<String>) {
        val port = 8000
        val obj: MenuInterface

        try {
            obj = Naming.lookup("rmi://localhost:$port/menu") as MenuInterface
            val x: String = obj.createNewServer()
            println(x)
            if (x != "0") {
                println("désolé, votre server n'a pas pu ce créer")
            } else {
                println("numéro du server : $x")
            }

        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }
}
