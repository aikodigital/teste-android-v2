package com.example.testeaiko.di;

import com.example.testeaiko.services.RoutesApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

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
public final class NetworkModule_ProvideRoutesApiServiceFactory implements Factory<RoutesApiService> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  public NetworkModule_ProvideRoutesApiServiceFactory(Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public RoutesApiService get() {
    return provideRoutesApiService(okHttpClientProvider.get());
  }

  public static NetworkModule_ProvideRoutesApiServiceFactory create(
      Provider<OkHttpClient> okHttpClientProvider) {
    return new NetworkModule_ProvideRoutesApiServiceFactory(okHttpClientProvider);
  }

  public static RoutesApiService provideRoutesApiService(OkHttpClient okHttpClient) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideRoutesApiService(okHttpClient));
  }
}
