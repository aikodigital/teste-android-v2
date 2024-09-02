package com.example.testeaiko.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/example/testeaiko/services/TransitApiService;", "", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "baseUrl", "", "retrofit", "Lcom/example/testeaiko/apis/TransitApi;", "createApi", "app_debug"})
public final class TransitApiService {
    @org.jetbrains.annotations.NotNull
    private final okhttp3.OkHttpClient okHttpClient = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String baseUrl = "https://api.olhovivo.sptrans.com.br/v2.1/";
    @org.jetbrains.annotations.Nullable
    private com.example.testeaiko.apis.TransitApi retrofit;
    
    public TransitApiService(@org.jetbrains.annotations.NotNull
    okhttp3.OkHttpClient okHttpClient) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.testeaiko.apis.TransitApi createApi() {
        return null;
    }
}