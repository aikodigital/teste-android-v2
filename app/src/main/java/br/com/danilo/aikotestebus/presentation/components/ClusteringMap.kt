package br.com.danilo.aikotestebus.presentation.components

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.danilo.aikotestebus.domain.model.entity.MapMarker
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_40
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_8
import com.google.maps.android.clustering.algo.NonHierarchicalViewBasedAlgorithm
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.clustering.rememberClusterManager
import com.google.maps.android.compose.clustering.rememberClusterRenderer

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun ClusteringMap(
    clusterItems: List<MapMarker>,
    markerIcon: Painter,
) {
    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp.dp
    val screenWidth = config.screenWidthDp.dp

    val clusterMgr = rememberClusterManager<MapMarker>()

    val clusterRenderer = rememberClusterRenderer(
        clusterContent = { cluster ->
            ClusterCircle(
                modifier = Modifier.size(spacing_40),
                label = cluster.size.toString(),
                backgroundColor = Color.Blue
            )
        },
        clusterItemContent = {
            Image(
                painter = markerIcon,
                contentDescription = "Ícone do ônibus no mapa"
            )
        },
        clusterManager = clusterMgr,

    )

    SideEffect {
        clusterMgr?.algorithm = NonHierarchicalViewBasedAlgorithm(
            screenWidth.value.toInt(),
            screenHeight.value.toInt()
        )

        clusterMgr?.setOnClusterClickListener {
            Log.d(TAG, "Cluster clicked! $it")
            false
        }
        clusterMgr?.setOnClusterItemClickListener {
            Log.d(TAG, "Cluster item clicked! $it")
            false
        }
        clusterMgr?.setOnClusterItemInfoWindowClickListener {
            Log.d(TAG, "Cluster item info window clicked! $it")
        }
    }

    SideEffect {
        if (clusterMgr?.renderer != clusterRenderer) {
            clusterMgr?.renderer = clusterRenderer ?: return@SideEffect
        }
    }

    clusterMgr?.let {
        Clustering(
            items = clusterItems,
            clusterManager = it
        )
    }
}

@Composable
fun ClusterCircle(
    modifier: Modifier = Modifier,
    label: String,
    backgroundColor: Color
) {
    Box(
        modifier = modifier
            .background(backgroundColor, CircleShape)
            .padding(spacing_8),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = Color.White,
            style = androidx.compose.ui.text.TextStyle(fontSize = 14.sp)
        )
    }
}