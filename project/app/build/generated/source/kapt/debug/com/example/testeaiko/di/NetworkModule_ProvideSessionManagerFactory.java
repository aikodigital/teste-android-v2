package com.example.testeaiko.di;

import com.example.testeaiko.SessionManager;
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
public final class NetworkModule_ProvideSessionManagerFactory implements Factory<SessionManager> {
  private final Provider<TransitApiService> apiServiceProvider;

  public NetworkModule_ProvideSessionManagerFactory(
      Provider<TransitApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public SessionManager get() {
    return provideSessionManager(apiServiceProvider.get());
  }

  public static NetworkModule_ProvideSessionManagerFactory create(
      Provider<TransitApiService> apiServiceProvider) {
    return new NetworkModule_ProvideSessionManagerFactory(apiServiceProvider);
  }

  public static SessionManager provideSessionManager(TransitApiService apiService) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideSessionManager(apiService));
  }
}
