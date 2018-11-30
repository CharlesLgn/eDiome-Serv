package test

import inter.ServerInterface
import java.rmi.Naming

object TestServer{

    @JvmStatic
    fun main(arg: Array<String>){
        val port = 8000
        val obj: ServerInterface

        try {
            obj = Naming.lookup("rmi://localhost:$port/serv0") as ServerInterface
            val messages = obj.messages
            println(messages)

            val  x = obj.messages.size+1

            obj.send("moi", "message $x")
            obj.send("moi", "message $x")
            obj.send("moi", "message $x")
            obj.send("moi", "message $x")
            obj.send("moi", "message $x")
            obj.send("moi", "message $x")
            obj.send("moi", "message $x")
            obj.send("moi", "message $x")
            obj.send("moi", "message $x")
            obj.send("moi", "message $x")
        } catch (e : Exception){
            e.printStackTrace()
        }


    }

}