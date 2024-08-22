package com.aiko.teste.sptrans.utils.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.NoOpUpdate
import com.aiko.teste.sptrans.R
import com.aiko.teste.sptrans.utils.getBitmapFromImage
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotation
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager

@Composable
fun MapBoxMap(
    modifier: Modifier = Modifier.fillMaxSize(),
    centerPoint: Point?,
    busStops: List<Point>?,
    handlePointClick: (PointAnnotation) -> Boolean
) {
    var pointAnnotationManager: PointAnnotationManager? by remember {
        mutableStateOf(null)
    }
    val context = LocalContext.current

    AndroidView(
        factory = {
            MapView(it).also { mapView ->
                mapView.mapboxMap.loadStyle(Style.STANDARD)
                val annotationApi = mapView.annotations
                pointAnnotationManager = annotationApi.createPointAnnotationManager()
            }
        },
        update = { mapView ->
            if (centerPoint != null) {
                mapView.mapboxMap
                    .flyTo(CameraOptions.Builder().zoom(12.0).center(centerPoint).build())
            }

            if (busStops != null) {
                pointAnnotationManager?.let {
                    busStops.forEach { busStop ->
                        val pointAnnotationOptions = PointAnnotationOptions()
                            .withPoint(busStop)
                            .withIconImage(
                                getBitmapFromImage(
                                    context,
                                    R.drawable.baseline_local_parking_24
                                )
                            )
                        it.create(pointAnnotationOptions)
                    }
                    it.addClickListener(handlePointClick)
                }
            }
            NoOpUpdate
        },
        modifier = modifier
    )
}