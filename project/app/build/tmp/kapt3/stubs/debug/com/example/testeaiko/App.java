package com.example.testeaiko;

@dagger.hilt.android.HiltAndroidApp
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/example/testeaiko/App;", "Landroid/app/Application;", "()V", "sessionManager", "Lcom/example/testeaiko/SessionManager;", "getSessionManager", "()Lcom/example/testeaiko/SessionManager;", "setSessionManager", "(Lcom/example/testeaiko/SessionManager;)V", "app_debug"})
public final class App extends android.app.Application {
    @javax.inject.Inject
    public com.example.testeaiko.SessionManager sessionManager;
    
    public App() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.testeaiko.SessionManager getSessionManager() {
        return null;
    }
    
    public final void setSessionManager(@org.jetbrains.annotations.NotNull
    com.example.testeaiko.SessionManager p0) {
    }
}