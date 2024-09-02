package com.example.testeaiko.di;

import com.example.testeaiko.services.TransitApiService;
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
public final class NetworkModule_ProvideTransitApiServiceFactory implements Factory<TransitApiService> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  public NetworkModule_ProvideTransitApiServiceFactory(
      Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public TransitApiService get() {
    return provideTransitApiService(okHttpClientProvider.get());
  }

  public static NetworkModule_ProvideTransitApiServiceFactory create(
      Provider<OkHttpClient> okHttpClientProvider) {
    return new NetworkModule_ProvideTransitApiServiceFactory(okHttpClientProvider);
  }

  public static TransitApiService provideTransitApiService(OkHttpClient okHttpClient) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideTransitApiService(okHttpClient));
  }
}
