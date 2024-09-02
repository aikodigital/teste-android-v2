package com.example.testeaiko.repositories;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J9\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u001e\u0010\n\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f\u0012\u0004\u0012\u00020\u00060\u000b\u00f8\u0001\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/example/testeaiko/repositories/RoutesApiRepository;", "Lcom/example/testeaiko/repositories/ApiRepository;", "apiService", "Lcom/example/testeaiko/services/RoutesApiService;", "(Lcom/example/testeaiko/services/RoutesApiService;)V", "getRoutes", "", "origin", "", "destination", "callback", "Lkotlin/Function1;", "Lkotlin/Result;", "Lretrofit2/Response;", "Lcom/example/testeaiko/datamodels/ModelRoute;", "app_debug"})
public final class RoutesApiRepository extends com.example.testeaiko.repositories.ApiRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.testeaiko.services.RoutesApiService apiService = null;
    
    public RoutesApiRepository(@org.jetbrains.annotations.NotNull
    com.example.testeaiko.services.RoutesApiService apiService) {
        super();
    }
    
    public final void getRoutes(@org.jetbrains.annotations.NotNull
    java.lang.String origin, @org.jetbrains.annotations.NotNull
    java.lang.String destination, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super kotlin.Result<retrofit2.Response<com.example.testeaiko.datamodels.ModelRoute>>, kotlin.Unit> callback) {
    }
}