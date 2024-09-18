package br.com.aikosptrans.presentation.atomic.template

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.aikosptrans.domain.entity.BusLine
import br.com.aikosptrans.domain.entity.BusStop
import br.com.aikosptrans.presentation.atomic.organism.BusLineDetailHeaderOrganism
import br.com.aikosptrans.presentation.atomic.organism.BusStopItemOrganism

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusLineDetailTemplate(
    busLine: BusLine?,
    busStops: List<BusStop>,
    onItemClicked: (BusStop) -> Unit,
    onBackClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onBackClicked
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        ) {
            Column {
                busLine?.let { line ->
                    BusLineDetailHeaderOrganism(
                        line = line
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    items(busStops) {
                        BusStopItemOrganism(
                            busStop = it,
                            onItemClicked = onItemClicked
                        )
                    }
                }
            }
        }
    }
}