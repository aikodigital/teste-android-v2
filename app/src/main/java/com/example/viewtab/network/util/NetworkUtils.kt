package com.example.viewtab.network.util

import com.example.viewtab.network.modelNerwork.Message

object NetworkUtils {

    const val UNEXPECTED_ERROR = 9000

    const val CONNECTION_FAILED = 9001

    const val UNAUTHORIZED = 401

    const val FORBIDDEN = 403

    const val NOT_FOUND = 404

    const val METHOD_NOT_ALLOWED = 405

    const val REQUEST_TIMEOUT = 408

    const val INTERNAL_SERVER_ERROR = 500

    const val NOT_IMPLEMENTED = 501

    const val SERVER_UNAVALIABLE = 503

    fun getErrorMessageByCode(code: Int): Message? {
        return when (code) {
            UNAUTHORIZED -> Message.make(
                "Falha na autenticação",
                "Não foi possivel autenticar com o servidor. Verifique com o administrador se o dispositivo foi cadastrado."
            )
            FORBIDDEN -> Message.make(
                "Falha na autenticação",
                "O dispositivo não possui autorização para acessar o servidor. Solicite o cadastro ao administrador."
            )
            NOT_FOUND -> Message.make("O recurso solicitado não foi encontrado ou não está disponível. Tente novamente mais tarde.")
            METHOD_NOT_ALLOWED -> Message.make("O método solicitado não é compatível com a requisição.")
            REQUEST_TIMEOUT -> Message.make(
                "Falha na conexão",
                "O tempo de resposta foi excedido. Verifique se o endereço do servidor está correto e se possui conexão com a rede."
            )
            INTERNAL_SERVER_ERROR -> Message.make(
                "Falha no servidor",
                "Ocorreu um erro interno no servidor."
            )
            NOT_IMPLEMENTED -> Message.make(
                "Falha no servidor",
                "O servidor não possui ou não suporta o método requerido."
            )
            SERVER_UNAVALIABLE -> Message.make("O servidor está indisponível. Tente novamente mais tarde.")
            CONNECTION_FAILED -> Message.make(
                "Falha na conexão",
                "Ocorreu um erro na conexão com o servidor. Verifique se o endereço e porta de conexão estão corretos."
            )
            UNEXPECTED_ERROR -> Message.make(
                "Falha desconhecida",
                "Ocorreu um erro inesperado ao tentar comunicação com o servidor. Contate o administrador."
            )
            else -> Message.make(
                "Falha desconhecida",
                "Ocorreu um erro inesperado ao tentar comunicação com o servidor. Contate o administrador."
            )
        }
    }
}