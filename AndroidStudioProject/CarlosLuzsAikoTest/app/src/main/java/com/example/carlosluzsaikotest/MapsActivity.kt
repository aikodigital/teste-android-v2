package com.example.carlosluzsaikotest

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.carlosluzsaikotest.data.model.PosicaoResponse
import com.example.carlosluzsaikotest.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val TAG: String = MapsActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val saoPaulo = LatLng(-23.5558, -46.6396)
//        mMap.addMarker(MarkerOptions().position(saoPaulo).title("Marcador em São Paulo capital!"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(saoPaulo, 15.0f))

        val token = getTokenFromMetaData()
        token?.let {
            getAllData(token, mMap)
        } ?: {Log.e(TAG, "Olho Vivo token is null!")}
    }

    private fun getTokenFromMetaData(): String? {
        try {
            val appInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
            val bundle = appInfo.metaData
            return bundle.getString("com.example.OLHO_VIVO_TOKEN")
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }

}




//TODO: Move it to the right place, use MVVM for God's sake

interface OlhoVivoApiService {
    @POST("Login/Autenticar")
    suspend fun authenticate(@Query("token") token: String): Response<Boolean>

    @GET("Posicao")
    suspend fun getVehiclePosition(): Response<PosicaoResponse>
}


object RetrofitClient {
    private const val BASE_URL = "https://api.olhovivo.sptrans.com.br/v2.1/"

    // Olho vivo authentication needs cookies!
    private val cookieJar = object : CookieJar {
        private val cookieStore: MutableMap<String, List<Cookie>> = mutableMapOf()

        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            cookieStore[url.host()] = cookies
        }

        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            return cookieStore[url.host()] ?: listOf()
        }
    }

    private val okHttpClient = OkHttpClient.Builder()
        .cookieJar(cookieJar)
        .build()

    val apiService: OlhoVivoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)  // Usando OkHttpClient com CookieJar
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OlhoVivoApiService::class.java)
    }
}

const val TAG = "TRECO"

private fun getAllData(token: String, mMap: GoogleMap) {
    CoroutineScope(Dispatchers.Main).launch {
        try {
            withContext(Dispatchers.IO) {

                // Authentication
                val isAuthenticated = authenticateUser(token)

                if (isAuthenticated) {

                    // REQ001: Posições dos veículos: Exibir no mapa onde os veículos estavam na sua última atualização.
                    val posicaoResponse = fetchLinesAndVehiclesPositions()
                    Log.d(TAG, posicaoResponse.toString())

                    withContext(Dispatchers.Main) {
                        posicaoResponse?.let {
                            val horario = it.hr
                            it.l.forEach { linha ->
                                linha.vs.forEach { veiculo ->
                                    // TODO: Add vehicles to the map, custom markers?
                                    val prefixo = veiculo.p
//                                    val acessivel = veiculo.a
                                    val latitude = veiculo.py
                                    val longitude = veiculo.px

                                    val vehicleLatLng = LatLng(latitude, longitude)
                                    val markerOptions = MarkerOptions()
                                        .position(vehicleLatLng)
                                        .title("$prefixo")
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_icon))
                                    mMap.addMarker(markerOptions)
                                    // Faça algo com os dados dos veículos
                                }
                            }
                        }
                    }

                    // Add vehicles to the map, custom markers?

                    // TODO: REQ002: Linhas: Exibir informações sobre as linhas de ônibus.

                    // TODO: REQ003: Paradas: Exibir os pontos de parada da cidade no mapa.

                    // TODO: REQ004: Previsão de chegada: Dado uma parada informar a previsão de chegada de cada veículo que passe pela parada selecionada.
                }
                Log.d(TAG, "getAllData coroutine (escopo IO) END!")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erro: ${e.message}")
        }
    }
}

suspend fun authenticateUser(token: String): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitClient.apiService.authenticate(token)

            // Verificar se a requisição foi bem-sucedida
            if (response.isSuccessful && response.body() == true) {
                Log.i(TAG, "Autenticado com Olho Vivo com sucesso!")
                true
            } else {
                Log.i(TAG,"Falha na autenticação com Olho Vivo")
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erro: ${e.message}")
            false
        }
    }
}

private suspend fun fetchLinesAndVehiclesPositions(): PosicaoResponse? {
    return try {
        val response = withContext(Dispatchers.IO) {
            RetrofitClient.apiService.getVehiclePosition()
        }

        if (response.isSuccessful) {
            val posicaoResponse = response.body()
            Log.i(TAG, posicaoResponse?.toString() ?: "Sem dados")
            posicaoResponse
        } else {
            Log.e(TAG, "Erro na requisição: ${response.code()}")
            null
        }
    } catch (e: Exception) {
        Log.e(TAG, "Erro: ${e.message}")
        null
    }
}