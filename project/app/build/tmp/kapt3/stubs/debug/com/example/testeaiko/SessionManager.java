package com.example.testeaiko;

@javax.inject.Singleton
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0002J\u0006\u0010\t\u001a\u00020\bJ\b\u0010\n\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/testeaiko/SessionManager;", "", "apiService", "Lcom/example/testeaiko/services/TransitApiService;", "(Lcom/example/testeaiko/services/TransitApiService;)V", "isAuthenticated", "", "authenticate", "", "handleAuthenticationFailure", "resetAuthentication", "app_debug"})
public final class SessionManager {
    @org.jetbrains.annotations.NotNull
    private final com.example.testeaiko.services.TransitApiService apiService = null;
    private boolean isAuthenticated = false;
    
    @javax.inject.Inject
    public SessionManager(@org.jetbrains.annotations.NotNull
    com.example.testeaiko.services.TransitApiService apiService) {
        super();
    }
    
    private final void authenticate() {
    }
    
    private final void resetAuthentication() {
    }
    
    public final void handleAuthenticationFailure() {
    }
}