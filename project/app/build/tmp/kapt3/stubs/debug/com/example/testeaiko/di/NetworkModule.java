package com.example.testeaiko.di;

@dagger.Module
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u0006H\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u0012H\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\tH\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u0012H\u0007J\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u0006H\u0007\u00a8\u0006\u001a"}, d2 = {"Lcom/example/testeaiko/di/NetworkModule;", "", "()V", "provideCookieJar", "Lokhttp3/CookieJar;", "provideOkHttpClient", "Lokhttp3/OkHttpClient;", "cookieJar", "provideRetrofit", "Lretrofit2/Retrofit;", "okHttpClient", "provideRoutesApiRepository", "Lcom/example/testeaiko/repositories/RoutesApiRepository;", "apiService", "Lcom/example/testeaiko/services/RoutesApiService;", "provideRoutesApiService", "provideSessionManager", "Lcom/example/testeaiko/SessionManager;", "Lcom/example/testeaiko/services/TransitApiService;", "provideTransitApi", "Lcom/example/testeaiko/apis/TransitApi;", "retrofit", "provideTransitApiRepository", "Lcom/example/testeaiko/repositories/TransitApiRepository;", "provideTransitApiService", "MyCookieJar", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class NetworkModule {
    @org.jetbrains.annotations.NotNull
    public static final com.example.testeaiko.di.NetworkModule INSTANCE = null;
    
    private NetworkModule() {
        super();
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final retrofit2.Retrofit provideRetrofit(@org.jetbrains.annotations.NotNull
    okhttp3.OkHttpClient okHttpClient) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.example.testeaiko.SessionManager provideSessionManager(@org.jetbrains.annotations.NotNull
    com.example.testeaiko.services.TransitApiService apiService) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.example.testeaiko.apis.TransitApi provideTransitApi(@org.jetbrains.annotations.NotNull
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.example.testeaiko.repositories.TransitApiRepository provideTransitApiRepository(@org.jetbrains.annotations.NotNull
    com.example.testeaiko.services.TransitApiService apiService) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.example.testeaiko.services.TransitApiService provideTransitApiService(@org.jetbrains.annotations.NotNull
    okhttp3.OkHttpClient okHttpClient) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.example.testeaiko.services.RoutesApiService provideRoutesApiService(@org.jetbrains.annotations.NotNull
    okhttp3.OkHttpClient okHttpClient) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.example.testeaiko.repositories.RoutesApiRepository provideRoutesApiRepository(@org.jetbrains.annotations.NotNull
    com.example.testeaiko.services.RoutesApiService apiService) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final okhttp3.CookieJar provideCookieJar() {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final okhttp3.OkHttpClient provideOkHttpClient(@org.jetbrains.annotations.NotNull
    okhttp3.CookieJar cookieJar) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0016R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/testeaiko/di/NetworkModule$MyCookieJar;", "Lokhttp3/CookieJar;", "()V", "cookieStore", "Ljava/util/concurrent/ConcurrentHashMap;", "", "", "Lokhttp3/Cookie;", "loadForRequest", "", "url", "Lokhttp3/HttpUrl;", "saveFromResponse", "", "cookies", "app_debug"})
    public static final class MyCookieJar implements okhttp3.CookieJar {
        @org.jetbrains.annotations.NotNull
        private final java.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.List<okhttp3.Cookie>> cookieStore = null;
        
        public MyCookieJar() {
            super();
        }
        
        @java.lang.Override
        public void saveFromResponse(@org.jetbrains.annotations.NotNull
        okhttp3.HttpUrl url, @org.jetbrains.annotations.NotNull
        java.util.List<okhttp3.Cookie> cookies) {
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.util.List<okhttp3.Cookie> loadForRequest(@org.jetbrains.annotations.NotNull
        okhttp3.HttpUrl url) {
            return null;
        }
    }
}