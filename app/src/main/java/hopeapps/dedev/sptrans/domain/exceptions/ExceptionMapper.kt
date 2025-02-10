package hopeapps.dedev.sptrans.domain.exceptions

sealed class DomainException(message: String) : Exception(message) {
    class ServerException : DomainException("Erro no servidor, tente novamente.")
    class NotFoundException : DomainException("Nenhum resultado encontrado.")
    class UnknownException : DomainException("Erro desconhecido.")
    class AuthenticationException: DomainException("Erro na autenticação")
}
