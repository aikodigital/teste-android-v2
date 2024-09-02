package com.example.testeaiko.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010)\n\u0002\u0010\'\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J&\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\u00182\u0006\u0010\u0019\u001a\u00020\u0012J\u0016\u0010\u001a\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u0012J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\nJ\u0006\u0010#\u001a\u00020\rJ\"\u0010$\u001a\u00020\r2\u0018\u0010%\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\'0&H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/example/testeaiko/util/MarkerHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "lastPolyline", "Lcom/google/android/gms/maps/model/Polyline;", "stopMarkers", "", "Lcom/google/android/gms/maps/model/LatLng;", "Lcom/google/android/gms/maps/model/Marker;", "vehicleMarkers", "addStopIconMarker", "", "mMap", "Lcom/google/android/gms/maps/GoogleMap;", "latLng", "stopCode", "", "scaleFactor", "", "addVehicleMarker", "title", "decodePolyline", "", "encoded", "drawRoute", "points", "getBitmapDescriptorFromVector", "Lcom/google/android/gms/maps/model/BitmapDescriptor;", "vectorResId", "", "isStopMarker", "", "marker", "removeAllMarkers", "removeMarkers", "iterator", "", "", "app_debug"})
public final class MarkerHelper {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<com.google.android.gms.maps.model.LatLng, com.google.android.gms.maps.model.Marker> vehicleMarkers = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<com.google.android.gms.maps.model.LatLng, com.google.android.gms.maps.model.Marker> stopMarkers = null;
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.maps.model.Polyline lastPolyline;
    
    public MarkerHelper(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    public final void addVehicleMarker(@org.jetbrains.annotations.NotNull
    com.google.android.gms.maps.GoogleMap mMap, @org.jetbrains.annotations.NotNull
    com.google.android.gms.maps.model.LatLng latLng, @org.jetbrains.annotations.NotNull
    java.lang.String title, float scaleFactor) {
    }
    
    public final void addStopIconMarker(@org.jetbrains.annotations.NotNull
    com.google.android.gms.maps.GoogleMap mMap, @org.jetbrains.annotations.NotNull
    com.google.android.gms.maps.model.LatLng latLng, @org.jetbrains.annotations.NotNull
    java.lang.String stopCode, float scaleFactor) {
    }
    
    private final com.google.android.gms.maps.model.BitmapDescriptor getBitmapDescriptorFromVector(int vectorResId, float scaleFactor) {
        return null;
    }
    
    private final void removeMarkers(java.util.Iterator<? extends java.util.Map.Entry<com.google.android.gms.maps.model.LatLng, com.google.android.gms.maps.model.Marker>> iterator) {
    }
    
    public final void removeAllMarkers() {
    }
    
    public final boolean isStopMarker(@org.jetbrains.annotations.NotNull
    com.google.android.gms.maps.model.Marker marker) {
        return false;
    }
    
    public final void drawRoute(@org.jetbrains.annotations.NotNull
    com.google.android.gms.maps.GoogleMap mMap, @org.jetbrains.annotations.NotNull
    java.lang.String points) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.google.android.gms.maps.model.LatLng> decodePolyline(@org.jetbrains.annotations.NotNull
    java.lang.String encoded) {
        return null;
    }
}