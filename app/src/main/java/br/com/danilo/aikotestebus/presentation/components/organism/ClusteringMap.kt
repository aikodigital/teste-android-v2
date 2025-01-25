package br.com.danilo.aikotestebus.presentation.components.organism

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.danilo.aikotestebus.domain.model.entity.MapMarker
import com.google.maps.android.clustering.algo.NonHierarchicalViewBasedAlgorithm
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.clustering.rememberClusterManager
import com.google.maps.android.compose.clustering.rememberClusterRenderer

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun ClusteringMap(
    clusterItems: List<MapMarker>,
    markerIcon: Painter
) {
    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp.dp
    val screenWidth = config.screenWidthDp.dp

    // Lembrando o ClusterManager dentro do @Composable
    val clusterMgr = rememberClusterManager<MapMarker>()

    // Criar a função de rendering dentro do composable
    val clusterRenderer = rememberClusterRenderer(
        clusterContent = { cluster ->
            ClusterCircle(
                modifier = Modifier.size(40.dp),
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
        clusterManager = clusterMgr
    )

    // Inicializando o ClusterManager e Renderer dentro de LaunchedEffect para garantir a execução após a composição
    LaunchedEffect(clusterMgr) {
        clusterMgr?.apply {
            // Definir o algoritmo de cluster
            algorithm = NonHierarchicalViewBasedAlgorithm(
                screenWidth.value.toInt(),
                screenHeight.value.toInt()
            )
            if (clusterMgr.renderer != clusterRenderer) {
                renderer = clusterRenderer ?: return@LaunchedEffect

            }
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
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = Color.White,
            style = androidx.compose.ui.text.TextStyle(fontSize = 14.sp)
        )
    }
}

