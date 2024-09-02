package com.example.testeaiko.datamodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\'\b\u0086\b\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u00a2\u0006\u0002\u0010\u0013J\u000b\u0010\'\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u00c6\u0003J\t\u0010+\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0019J\t\u0010.\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010!J\u000b\u00100\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u009c\u0001\u00103\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00052\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u00c6\u0001\u00a2\u0006\u0002\u00104J\u0013\u00105\u001a\u00020\b2\b\u00106\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00107\u001a\u00020\u0005H\u00d6\u0001J\t\u00108\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0015R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&\u00a8\u00069"}, d2 = {"Lcom/example/testeaiko/datamodels/ModelLinha;", "", "c", "", "cl", "", "lt", "lc", "", "sl", "tl", "lt0", "lt1", "tp", "ts", "qv", "vs", "", "Lcom/example/testeaiko/datamodels/ModelVeiculo;", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/Boolean;ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;)V", "getC", "()Ljava/lang/String;", "getCl", "()I", "getLc", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getLt", "getLt0", "getLt1", "getQv", "getSl", "getTl", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTp", "getTs", "getVs", "()Ljava/util/List;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/Boolean;ILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/List;)Lcom/example/testeaiko/datamodels/ModelLinha;", "equals", "other", "hashCode", "toString", "app_debug"})
public final class ModelLinha {
    @org.jetbrains.annotations.Nullable
    private final java.lang.String c = null;
    private final int cl = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String lt = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Boolean lc = null;
    private final int sl = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer tl = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String lt0 = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String lt1 = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String tp = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String ts = null;
    private final int qv = 0;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.testeaiko.datamodels.ModelVeiculo> vs = null;
    
    public ModelLinha(@org.jetbrains.annotations.Nullable
    java.lang.String c, int cl, @org.jetbrains.annotations.Nullable
    java.lang.String lt, @org.jetbrains.annotations.Nullable
    java.lang.Boolean lc, int sl, @org.jetbrains.annotations.Nullable
    java.lang.Integer tl, @org.jetbrains.annotations.Nullable
    java.lang.String lt0, @org.jetbrains.annotations.Nullable
    java.lang.String lt1, @org.jetbrains.annotations.Nullable
    java.lang.String tp, @org.jetbrains.annotations.Nullable
    java.lang.String ts, int qv, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.testeaiko.datamodels.ModelVeiculo> vs) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getC() {
        return null;
    }
    
    public final int getCl() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean getLc() {
        return null;
    }
    
    public final int getSl() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getTl() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLt0() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLt1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTp() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTs() {
        return null;
    }
    
    public final int getQv() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.testeaiko.datamodels.ModelVeiculo> getVs() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    public final int component11() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.testeaiko.datamodels.ModelVeiculo> component12() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Boolean component4() {
        return null;
    }
    
    public final int component5() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.testeaiko.datamodels.ModelLinha copy(@org.jetbrains.annotations.Nullable
    java.lang.String c, int cl, @org.jetbrains.annotations.Nullable
    java.lang.String lt, @org.jetbrains.annotations.Nullable
    java.lang.Boolean lc, int sl, @org.jetbrains.annotations.Nullable
    java.lang.Integer tl, @org.jetbrains.annotations.Nullable
    java.lang.String lt0, @org.jetbrains.annotations.Nullable
    java.lang.String lt1, @org.jetbrains.annotations.Nullable
    java.lang.String tp, @org.jetbrains.annotations.Nullable
    java.lang.String ts, int qv, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.testeaiko.datamodels.ModelVeiculo> vs) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}