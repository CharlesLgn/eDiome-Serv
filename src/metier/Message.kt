package metier

import java.io.Serializable
import java.time.LocalDate

class Message(var pseudo: String?, var date: LocalDate?, var contenu: String?) : Serializable {

    override fun toString(): String {
        return pseudo + ' '.toString() + date +
                " - " + contenu + '\n'.toString()
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
