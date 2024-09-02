package com.example.testeaiko.viewmodels;

import com.example.testeaiko.repositories.RoutesApiRepository;
import com.example.testeaiko.repositories.TransitApiRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class MapViewModel_Factory implements Factory<MapViewModel> {
  private final Provider<TransitApiRepository> transitApiRepositoryProvider;

  private final Provider<RoutesApiRepository> routesApiRepositoryProvider;

  public MapViewModel_Factory(Provider<TransitApiRepository> transitApiRepositoryProvider,
      Provider<RoutesApiRepository> routesApiRepositoryProvider) {
    this.transitApiRepositoryProvider = transitApiRepositoryProvider;
    this.routesApiRepositoryProvider = routesApiRepositoryProvider;
  }

  @Override
  public MapViewModel get() {
    return newInstance(transitApiRepositoryProvider.get(), routesApiRepositoryProvider.get());
  }

  public static MapViewModel_Factory create(
      Provider<TransitApiRepository> transitApiRepositoryProvider,
      Provider<RoutesApiRepository> routesApiRepositoryProvider) {
    return new MapViewModel_Factory(transitApiRepositoryProvider, routesApiRepositoryProvider);
  }

  public static MapViewModel newInstance(TransitApiRepository transitApiRepository,
      RoutesApiRepository routesApiRepository) {
    return new MapViewModel(transitApiRepository, routesApiRepository);
  }
}
