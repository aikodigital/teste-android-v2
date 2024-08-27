package com.cesarsoftdevelopment.aikopublictransport.di.modules

import com.cesarsoftdevelopment.aikopublictransport.domain.repository.BusLinesRepository
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.EstimatedArrivalTimesRepository
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.StopsRepository
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.TransportInfoRepository
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.UserRepository
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.VehiclesPositionRepository
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.AuthenticateUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetBusLinesUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetEstimatedArrivalTimesByStopUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetSavedBusLinesUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetStopsByLineUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetVehiclesPositionByLineUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.SaveBusLinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideAuthenticateUseCase(userRepository: UserRepository) : AuthenticateUseCase {
        return AuthenticateUseCase(userRepository)
    }

    @Provides
    fun provideGetBusLinesUseCase(busLinesRepository: BusLinesRepository) : GetBusLinesUseCase {
        return GetBusLinesUseCase(busLinesRepository)
    }

    @Provides
    fun provideGetVehiclesPositionByLineUseCase(
        vehiclesPositionRepository: VehiclesPositionRepository
    ) : GetVehiclesPositionByLineUseCase {
        return GetVehiclesPositionByLineUseCase(vehiclesPositionRepository)
    }

    @Provides
    fun provideGetStopsByLineUseCase(stopsRepository: StopsRepository) : GetStopsByLineUseCase {
        return GetStopsByLineUseCase(stopsRepository)
    }

    @Provides
    fun provideGetEstimatedArrivalTimesByStopUseCase(
        estimatedArrivalTimesRepository: EstimatedArrivalTimesRepository
    ) : GetEstimatedArrivalTimesByStopUseCase {
        return GetEstimatedArrivalTimesByStopUseCase(estimatedArrivalTimesRepository)
    }

    @Provides
    fun provideGetSavedBusLinesUseCase(
        transportInfoRepository: TransportInfoRepository
    ) : GetSavedBusLinesUseCase {
        return GetSavedBusLinesUseCase(transportInfoRepository)
    }

    @Provides
    fun provideSaveBusLinesUseCase(
        transportInfoRepository: TransportInfoRepository
    ) : SaveBusLinesUseCase {
        return SaveBusLinesUseCase(transportInfoRepository)
    }


}