package br.com.danilo.aikotestebus.domain.usecase

import retrofit2.HttpException
import br.com.danilo.aikotestebus.data.repository.IBusRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import kotlinx.coroutines.flow.Flow

class SplashUseCase(
    private val repository: IBusRepository
) {

    suspend fun authenticatorSplash(): Flow<Result<Boolean>> {
        return flow {
            try {
                val isAuthenticated = repository.authenticator()
                emit(Result.success(isAuthenticated))
            } catch (ex: IOException) {
                emit(Result.failure(ex))
            } catch (ex: HttpException) {
                emit(Result.failure(ex))
            }
        }
    }

}