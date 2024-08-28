package com.example.viewtab.network.modelNerwork

import com.google.common.base.Strings

data class Message(
    val header: String? = null,
    val body: String? = null
) {

    companion object {

        fun empty(): Message {
            return Message("")
        }

        fun error(message: String?): Message {
            return Message("Error", message)
        }


        fun make(
            header: String?,
            message: String?
        ): Message {
            return Message(header, message)
        }

        fun make(message: String?): Message {
            if (Strings.isNullOrEmpty(message)) {
                return empty()
            }

            return Message(
                "Erro na validação de dados",
                message
            )
        }

    }
}

