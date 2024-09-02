package com.example.testeaiko;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class App_MembersInjector implements MembersInjector<App> {
  private final Provider<SessionManager> sessionManagerProvider;

  public App_MembersInjector(Provider<SessionManager> sessionManagerProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
  }

  public static MembersInjector<App> create(Provider<SessionManager> sessionManagerProvider) {
    return new App_MembersInjector(sessionManagerProvider);
  }

  @Override
  public void injectMembers(App instance) {
    injectSessionManager(instance, sessionManagerProvider.get());
  }

  @InjectedFieldSignature("com.example.testeaiko.App.sessionManager")
  public static void injectSessionManager(App instance, SessionManager sessionManager) {
    instance.sessionManager = sessionManager;
  }
}
