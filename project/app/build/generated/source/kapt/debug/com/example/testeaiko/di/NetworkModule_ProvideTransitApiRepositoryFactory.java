package com.example.testeaiko.di;

import com.example.testeaiko.repositories.TransitApiRepository;
import com.example.testeaiko.services.TransitApiService;
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
public final class NetworkModule_ProvideTransitApiRepositoryFactory implements Factory<TransitApiRepository> {
  private final Provider<TransitApiService> apiServiceProvider;

  public NetworkModule_ProvideTransitApiRepositoryFactory(
      Provider<TransitApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public TransitApiRepository get() {
    return provideTransitApiRepository(apiServiceProvider.get());
  }

  public static NetworkModule_ProvideTransitApiRepositoryFactory create(
      Provider<TransitApiService> apiServiceProvider) {
    return new NetworkModule_ProvideTransitApiRepositoryFactory(apiServiceProvider);
  }

  public static TransitApiRepository provideTransitApiRepository(TransitApiService apiService) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideTransitApiRepository(apiService));
  }
}
