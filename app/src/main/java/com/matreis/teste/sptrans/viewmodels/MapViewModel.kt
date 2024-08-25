package com.matreis.teste.sptrans.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.data.kml.KmlLayer
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.data.preferences.UserPreferences
import com.matreis.teste.sptrans.domain.model.BusStop
import com.matreis.teste.sptrans.domain.model.Line
import com.matreis.teste.sptrans.domain.model.MapMarkers
import com.matreis.teste.sptrans.domain.model.Vehicle
import com.matreis.teste.sptrans.domain.usecase.GetBusStopUseCase
import com.matreis.teste.sptrans.domain.usecase.GetRoadSpeedUseCase
import com.matreis.teste.sptrans.domain.usecase.GetVehiclePositionUseCase
import com.matreis.teste.sptrans.helper.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getBusStopUseCase: GetBusStopUseCase,
    private val getVehiclePositionUseCase: GetVehiclePositionUseCase,
    private val getRoadSpeedUseCase: GetRoadSpeedUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private var keepUpdating = true
    private var updateInterval = 0;

    private val _selectedLine = MutableLiveData<Line>()
    val selectedLine: LiveData<Line> get() = _selectedLine

    private val _error = MutableLiveData<Event<Int>>()
    val error: LiveData<Event<Int>> get() = _error

    private val _markers = MutableLiveData<MapMarkers>()
    val markers: LiveData<MapMarkers> get() = _markers

    private val _kmlLayer = MutableLiveData<KmlLayer?>()
    val kmlLayer: LiveData<KmlLayer?> get() = _kmlLayer

    private var busStopList = listOf<BusStop>()

    fun getLinesInformation(lineCode: Long) {
        viewModelScope.launch {
            keepUpdating = userPreferences.getAutoUpdate()
            updateInterval = userPreferences.getAutoUpdateInterval()
            try {
                val busStops = getBusStopByLine(lineCode)
                busStopList = busStops
                do {
                    Log.i("MapViewModel", "Getting vehicle positions for line: $lineCode")
                    val vehiclePositions = getVehiclePositionByLine(lineCode)
                    _markers.postValue(MapMarkers(busStopList, vehiclePositions))
                    delay(updateInterval.toDuration(DurationUnit.SECONDS))
                }while (isActive && keepUpdating)
            }catch (e: Exception){
                e.printStackTrace()
                _error.value = Event(R.string.error_get_lines_informations)
            }
        }
    }

    private suspend fun getBusStopByLine(lineCode: Long): List<BusStop> = getBusStopUseCase(lineCode).body() ?: emptyList()

    private suspend fun getVehiclePositionByLine(lineCode: Long): List<Vehicle> = getVehiclePositionUseCase(lineCode).body()?.vehicles ?: emptyList()

    fun setSelectedLine(it: Line) {
        _selectedLine.value = it
    }

    fun getBusStopList(): List<BusStop> = busStopList

    fun getRoadSpeed(context: Context, googleMap: GoogleMap) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val kmlFile = File(context.getExternalFilesDir(null), "TA.kml")
                if(kmlFile.exists()) {
                    val inputStream = FileInputStream(kmlFile)
                    val kmlLayer = KmlLayer(googleMap, inputStream, context)
                    _kmlLayer.postValue(kmlLayer)
                }else {
                    val fileName = "road_speed.kmz"
                    val externalFile = File(context.getExternalFilesDir(null), fileName)
                    val downloadedFilePath = downloadFile(externalFile)
                    downloadedFilePath?.let {
                        val outputZipDir = context.getExternalFilesDir(null)
                        val file = unzipFile(externalFile, outputZipDir!!)
                        file?.let {
                            val inputStream = FileInputStream(it)
                            val kmlLayer = KmlLayer(googleMap, inputStream, context)
                            _kmlLayer.postValue(kmlLayer)
                        }
                    }
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun downloadFile(outputDir: File): String? {
        val response = getRoadSpeedUseCase()
        if (response.isSuccessful) {
            /*val fileName = "road_speed.kmz"
            val externalFile = File(context.getExternalFilesDir(null), fileName)*/
            //val outputDir = context.getExternalFilesDir(null)
            val roadSpeed = response.body()?.byteStream()
            outputDir.outputStream().use { outputStream ->
                val buffer = ByteArray(4 * 1024) // Buffer size
                var bytesRead: Int
                while (roadSpeed!!.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }
                outputStream.flush()
                return outputDir.absolutePath
            }
        }
        return null
    }

    private fun unzipFile(zipFile: File, outputDir: File): File? {
        ZipInputStream(FileInputStream(zipFile)).use { zis ->
            var entry: ZipEntry? = zis.nextEntry
            var kmlFile: File?  = null
            while (entry != null) {
                if (entry.name.endsWith(".kml")) {
                    kmlFile = File(outputDir, entry.name)
                    FileOutputStream(kmlFile).use { fos ->
                        zis.copyTo(fos)
                    }
                    break
                }
                zis.closeEntry()
                entry = zis.nextEntry
            }
            zis.closeEntry()
            return kmlFile
        }
    }

}

