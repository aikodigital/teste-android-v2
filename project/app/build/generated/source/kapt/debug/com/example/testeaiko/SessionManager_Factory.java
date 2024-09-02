package com.example.testeaiko;

import com.example.testeaiko.services.TransitApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class SessionManager_Factory implements Factory<SessionManager> {
  private final Provider<TransitApiService> apiServiceProvider;

  public SessionManager_Factory(Provider<TransitApiService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public SessionManager get() {
    return newInstance(apiServiceProvider.get());
  }

  public static SessionManager_Factory create(Provider<TransitApiService> apiServiceProvider) {
    return new SessionManager_Factory(apiServiceProvider);
  }

  public static SessionManager newInstance(TransitApiService apiService) {
    return new SessionManager(apiService);
  }
}
