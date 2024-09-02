package com.example.testeaiko.fragments;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00b2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010,\u001a\u00020-H\u0002J\u0010\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u00020-H\u0002J\u0010\u00102\u001a\u00020-2\u0006\u00103\u001a\u000204H\u0002J\b\u00105\u001a\u00020-H\u0002J\b\u00106\u001a\u00020-H\u0002J\u0010\u00107\u001a\u00020-2\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010:\u001a\u00020-2\u0006\u0010;\u001a\u00020<H\u0002J \u0010=\u001a\u00020\u00102\u0006\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020\u00122\u0006\u0010@\u001a\u000209H\u0002J\b\u0010A\u001a\u00020-H\u0002J$\u0010B\u001a\u00020<2\u0006\u0010C\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010F2\b\u0010G\u001a\u0004\u0018\u00010HH\u0016J\b\u0010I\u001a\u00020-H\u0016J\b\u0010J\u001a\u00020-H\u0016J\u0010\u0010K\u001a\u00020-2\u0006\u0010L\u001a\u00020\u0014H\u0016J\b\u0010M\u001a\u00020-H\u0016J\b\u0010N\u001a\u00020-H\u0016J\u001a\u0010O\u001a\u00020-2\u0006\u0010;\u001a\u00020<2\b\u0010G\u001a\u0004\u0018\u00010HH\u0016J\b\u0010P\u001a\u00020-H\u0002J\b\u0010Q\u001a\u00020-H\u0002J\b\u0010R\u001a\u00020-H\u0002J\b\u0010S\u001a\u00020-H\u0002J\b\u0010T\u001a\u00020-H\u0002J\b\u0010U\u001a\u00020-H\u0002J\b\u0010V\u001a\u00020-H\u0002J\b\u0010W\u001a\u00020-H\u0002J\b\u0010X\u001a\u00020-H\u0002J\b\u0010Y\u001a\u00020-H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0016\u0010!\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082D\u00a2\u0006\u0002\n\u0000R\u001b\u0010&\u001a\u00020\'8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b(\u0010)\u00a8\u0006Z"}, d2 = {"Lcom/example/testeaiko/fragments/MapFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "binding", "Lcom/example/testeaiko/databinding/FragmentMapBinding;", "bottomSheetBehavior", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "Landroid/widget/FrameLayout;", "filterAdapter", "Lcom/example/testeaiko/adapters/SearchFilterAdapter;", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "handler", "Landroid/os/Handler;", "isUserInteracting", "", "lastStop", "Lcom/google/android/gms/maps/model/LatLng;", "mMap", "Lcom/google/android/gms/maps/GoogleMap;", "mapView", "Lcom/google/android/gms/maps/MapView;", "markerHelper", "Lcom/example/testeaiko/util/MarkerHelper;", "positions", "Lcom/example/testeaiko/datamodels/ModelPosicao;", "sessionManager", "Lcom/example/testeaiko/SessionManager;", "getSessionManager", "()Lcom/example/testeaiko/SessionManager;", "setSessionManager", "(Lcom/example/testeaiko/SessionManager;)V", "stops", "", "Lcom/example/testeaiko/datamodels/ModelParada;", "updateInterval", "", "viewModel", "Lcom/example/testeaiko/viewmodels/MapViewModel;", "getViewModel", "()Lcom/example/testeaiko/viewmodels/MapViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "enableMyLocation", "", "fetchMarkerInfo", "markerTitle", "", "fetchPositions", "fetchRoute", "modelVeiculo", "Lcom/example/testeaiko/datamodels/ModelVeiculo;", "fetchStops", "handleEmptyResult", "handleErrorCode", "code", "", "initializeInfoSheet", "view", "Landroid/view/View;", "isWithinRadius", "center", "point", "radius", "moveToCurrentLocation", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onLowMemory", "onMapReady", "googleMap", "onPause", "onResume", "onViewCreated", "setupArrivalsObserver", "setupLinesSearchObserver", "setupObservers", "setupRoutesObserver", "setupSearchFilters", "setupSearchView", "setupStopsObserver", "setupVehicleObserver", "startPositionUpdates", "updateMarkers", "app_debug"})
public final class MapFragment extends androidx.fragment.app.Fragment implements com.google.android.gms.maps.OnMapReadyCallback {
    private com.example.testeaiko.databinding.FragmentMapBinding binding;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy viewModel$delegate = null;
    private com.google.android.gms.maps.GoogleMap mMap;
    private com.google.android.gms.maps.MapView mapView;
    @org.jetbrains.annotations.Nullable
    private com.example.testeaiko.datamodels.ModelPosicao positions;
    @org.jetbrains.annotations.Nullable
    private java.util.List<com.example.testeaiko.datamodels.ModelParada> stops;
    private com.example.testeaiko.util.MarkerHelper markerHelper;
    private boolean isUserInteracting = false;
    @org.jetbrains.annotations.NotNull
    private final android.os.Handler handler = null;
    private final long updateInterval = 5000L;
    private com.google.android.material.bottomsheet.BottomSheetBehavior<android.widget.FrameLayout> bottomSheetBehavior;
    private com.example.testeaiko.adapters.SearchFilterAdapter filterAdapter;
    private com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient;
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.maps.model.LatLng lastStop;
    public com.example.testeaiko.SessionManager sessionManager;
    
    public MapFragment() {
        super();
    }
    
    private final com.example.testeaiko.viewmodels.MapViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.testeaiko.SessionManager getSessionManager() {
        return null;
    }
    
    public final void setSessionManager(@org.jetbrains.annotations.NotNull
    com.example.testeaiko.SessionManager p0) {
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeInfoSheet(android.view.View view) {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupLinesSearchObserver() {
    }
    
    private final void setupArrivalsObserver() {
    }
    
    private final void fetchRoute(com.example.testeaiko.datamodels.ModelVeiculo modelVeiculo) {
    }
    
    private final void setupRoutesObserver() {
    }
    
    private final void setupStopsObserver() {
    }
    
    private final void setupVehicleObserver() {
    }
    
    private final void setupSearchView() {
    }
    
    private final void setupSearchFilters() {
    }
    
    private final void fetchMarkerInfo(java.lang.String markerTitle) {
    }
    
    private final void handleErrorCode(int code) {
    }
    
    private final void handleEmptyResult() {
    }
    
    private final void startPositionUpdates() {
    }
    
    private final void fetchPositions() {
    }
    
    private final void fetchStops() {
    }
    
    private final void updateMarkers() {
    }
    
    private final boolean isWithinRadius(com.google.android.gms.maps.model.LatLng center, com.google.android.gms.maps.model.LatLng point, int radius) {
        return false;
    }
    
    @java.lang.Override
    public void onMapReady(@org.jetbrains.annotations.NotNull
    com.google.android.gms.maps.GoogleMap googleMap) {
    }
    
    private final void enableMyLocation() {
    }
    
    private final void moveToCurrentLocation() {
    }
    
    @java.lang.Override
    public void onResume() {
    }
    
    @java.lang.Override
    public void onPause() {
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @java.lang.Override
    public void onLowMemory() {
    }
}