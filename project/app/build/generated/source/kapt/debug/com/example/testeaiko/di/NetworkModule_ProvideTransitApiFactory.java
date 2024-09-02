package com.example.testeaiko.di;

import com.example.testeaiko.apis.TransitApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideTransitApiFactory implements Factory<TransitApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideTransitApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public TransitApi get() {
    return provideTransitApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideTransitApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideTransitApiFactory(retrofitProvider);
  }

  public static TransitApi provideTransitApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideTransitApi(retrofit));
  }
}
