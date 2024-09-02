package com.example.testeaiko.apis;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J(\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u00032\b\b\u0001\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J(\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\b0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u0006H\'J\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\"\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\b\b\u0001\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0013"}, d2 = {"Lcom/example/testeaiko/apis/TransitApi;", "", "authenticate", "Lretrofit2/Call;", "", "token", "", "getLinhaBuscar", "", "Lcom/example/testeaiko/datamodels/ModelLinha;", "termosBusca", "getParada", "Lcom/example/testeaiko/datamodels/ModelParada;", "getPosicao", "Lcom/example/testeaiko/datamodels/ModelPosicao;", "getPrevisaoParada", "Lcom/example/testeaiko/datamodels/ModelPrevisaoParada;", "codigoParada", "", "app_debug"})
public abstract interface TransitApi {
    
    @retrofit2.http.POST(value = "Login/Autenticar")
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<java.lang.Boolean> authenticate(@retrofit2.http.Query(value = "token")
    @org.jetbrains.annotations.NotNull
    java.lang.String token);
    
    @retrofit2.http.GET(value = "Posicao")
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<com.example.testeaiko.datamodels.ModelPosicao> getPosicao(@retrofit2.http.Query(value = "token")
    @org.jetbrains.annotations.NotNull
    java.lang.String token);
    
    @retrofit2.http.GET(value = "Parada/Buscar")
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<java.util.List<com.example.testeaiko.datamodels.ModelParada>> getParada(@retrofit2.http.Query(value = "token")
    @org.jetbrains.annotations.NotNull
    java.lang.String token, @retrofit2.http.Query(value = "termosBusca")
    @org.jetbrains.annotations.NotNull
    java.lang.String termosBusca);
    
    @retrofit2.http.GET(value = "Previsao/Parada")
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<com.example.testeaiko.datamodels.ModelPrevisaoParada> getPrevisaoParada(@retrofit2.http.Query(value = "codigoParada")
    int codigoParada, @retrofit2.http.Query(value = "token")
    @org.jetbrains.annotations.NotNull
    java.lang.String token);
    
    @retrofit2.http.GET(value = "Linha/Buscar")
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<java.util.List<com.example.testeaiko.datamodels.ModelLinha>> getLinhaBuscar(@retrofit2.http.Query(value = "termosBusca")
    @org.jetbrains.annotations.NotNull
    java.lang.String termosBusca, @retrofit2.http.Query(value = "token")
    @org.jetbrains.annotations.NotNull
    java.lang.String token);
}