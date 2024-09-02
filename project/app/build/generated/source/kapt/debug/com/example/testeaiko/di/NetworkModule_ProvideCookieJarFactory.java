package com.example.testeaiko.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.CookieJar;

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
public final class NetworkModule_ProvideCookieJarFactory implements Factory<CookieJar> {
  @Override
  public CookieJar get() {
    return provideCookieJar();
  }

  public static NetworkModule_ProvideCookieJarFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CookieJar provideCookieJar() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideCookieJar());
  }

  private static final class InstanceHolder {
    private static final NetworkModule_ProvideCookieJarFactory INSTANCE = new NetworkModule_ProvideCookieJarFactory();
  }
}
