package br.com.aiko.projetoolhovivo.ui.position

import br.com.aiko.projetoolhovivo.data.model.stop.Stop
import br.com.aiko.projetoolhovivo.data.model.vehicle.Vehicle

data class PositionMap(
    var stops: List<Stop>,
    var vehicles: List<Vehicle>,
    var lastUpdate: String
)