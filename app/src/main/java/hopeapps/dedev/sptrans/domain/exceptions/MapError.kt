package hopeapps.dedev.sptrans.domain.exceptions

import hopeapps.dedev.sptrans.data.network.ApiResponse


fun mapError(response: ApiResponse.Error): DomainException {
    return when (response.statusCode) {
        404 -> DomainException.NotFoundException()
        500 -> DomainException.ServerException()
        else -> DomainException.UnknownException()
    }
}