package com.example.testeaiko.di;

import com.example.testeaiko.repositories.RoutesApiRepository;
import com.example.testeaiko.services.RoutesApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NetworkModule_ProvideRoutesApiRepositoryFactory implements Factory<RoutesApiRepository> {
  private final Provider<RoutesApiService> apiServiceProvider;

  public NetworkModule_ProvideRoutesApiRepositoryFactory(
      Provider<RoutesApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public RoutesApiRepository get() {
    return provideRoutesApiRepository(apiServiceProvider.get());
  }

  public static NetworkModule_ProvideRoutesApiRepositoryFactory create(
      Provider<RoutesApiService> apiServiceProvider) {
    return new NetworkModule_ProvideRoutesApiRepositoryFactory(apiServiceProvider);
  }

  public static RoutesApiRepository provideRoutesApiRepository(RoutesApiService apiService) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideRoutesApiRepository(apiService));
  }
}
