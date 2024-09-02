package com.example.testeaiko.datamodels

data class ModelRoute(
        val routes: List<Route>
)

data class Route(
        val overview_polyline: Polyline,
        val legs: List<Leg>
)

data class Leg(
        val distance: Distance,
        val duration: Duration,
        val start_location: Location,
        val end_location: Location
)

data class Distance(
        val text: String,
        val value: Int
)

data class Duration(
        val text: String,
        val value: Int
)

data class Location(
        val lat: Double,
        val lng: Double
)

data class Polyline(
        val points: String
)
