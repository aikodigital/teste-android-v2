package com.example.testeaiko.repositories;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J7\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2$\u0010\t\u001a \u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u000b\u0012\u0004\u0012\u00020\u00060\n\u00f8\u0001\u0000J/\u0010\u000f\u001a\u00020\u00062$\u0010\t\u001a \u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\r0\f0\u000b\u0012\u0004\u0012\u00020\u00060\n\u00f8\u0001\u0000J)\u0010\u0011\u001a\u00020\u00062\u001e\u0010\t\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\f0\u000b\u0012\u0004\u0012\u00020\u00060\n\u00f8\u0001\u0000J1\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u001e\u0010\t\u001a\u001a\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\f0\u000b\u0012\u0004\u0012\u00020\u00060\n\u00f8\u0001\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/example/testeaiko/repositories/TransitApiRepository;", "Lcom/example/testeaiko/repositories/ApiRepository;", "apiService", "Lcom/example/testeaiko/services/TransitApiService;", "(Lcom/example/testeaiko/services/TransitApiService;)V", "getLinhaBuscar", "", "termosBusca", "", "callback", "Lkotlin/Function1;", "Lkotlin/Result;", "Lretrofit2/Response;", "", "Lcom/example/testeaiko/datamodels/ModelLinha;", "getPontos", "Lcom/example/testeaiko/datamodels/ModelParada;", "getPosicao", "Lcom/example/testeaiko/datamodels/ModelPosicao;", "getPrevisaoParada", "codigoParada", "", "Lcom/example/testeaiko/datamodels/ModelPrevisaoParada;", "app_debug"})
public final class TransitApiRepository extends com.example.testeaiko.repositories.ApiRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.testeaiko.services.TransitApiService apiService = null;
    
    public TransitApiRepository(@org.jetbrains.annotations.NotNull
    com.example.testeaiko.services.TransitApiService apiService) {
        super();
    }
    
    public final void getPosicao(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super kotlin.Result<retrofit2.Response<com.example.testeaiko.datamodels.ModelPosicao>>, kotlin.Unit> callback) {
    }
    
    public final void getPontos(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super kotlin.Result<retrofit2.Response<java.util.List<com.example.testeaiko.datamodels.ModelParada>>>, kotlin.Unit> callback) {
    }
    
    public final void getPrevisaoParada(int codigoParada, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super kotlin.Result<retrofit2.Response<com.example.testeaiko.datamodels.ModelPrevisaoParada>>, kotlin.Unit> callback) {
    }
    
    public final void getLinhaBuscar(@org.jetbrains.annotations.NotNull
    java.lang.String termosBusca, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super kotlin.Result<retrofit2.Response<java.util.List<com.example.testeaiko.datamodels.ModelLinha>>>, kotlin.Unit> callback) {
    }
}