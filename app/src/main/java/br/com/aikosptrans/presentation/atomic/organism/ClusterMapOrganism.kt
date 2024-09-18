package br.com.aikosptrans.presentation.atomic.organism

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import br.com.aikosptrans.domain.entity.ClusterData
import br.com.aikosptrans.presentation.atomic.atom.CircleAtom
import br.com.aikosptrans.ui.theme.Colors
import com.google.maps.android.clustering.algo.NonHierarchicalViewBasedAlgorithm
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.clustering.rememberClusterManager
import com.google.maps.android.compose.clustering.rememberClusterRenderer

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun ClusterMapOrganism(
    items: List<ClusterData>,
    iconMarker: Painter
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val clusterManager = rememberClusterManager<ClusterData>()

    clusterManager?.algorithm = NonHierarchicalViewBasedAlgorithm(
        screenWidth.value.toInt(),
        screenHeight.value.toInt()
    )

    val renderer = rememberClusterRenderer(
        clusterContent = { cluster ->
            CircleAtom(
                modifier = Modifier.size(40.dp),
                text = cluster.size.toString(),
                color = Colors.blue,
            )
        },
        clusterItemContent = {
            Image(
                painter = iconMarker,
                contentDescription = null
            )
        },
        clusterManager = clusterManager,
    )

    SideEffect {
        if (clusterManager?.renderer != renderer) {
            clusterManager?.renderer = renderer ?: return@SideEffect
        }
    }

    if (clusterManager != null) {
        Clustering(
            items = items,
            clusterManager = clusterManager,
        )
    }
}