package com.example.aikospbus

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.aikospbus.databinding.FragmentMainBinding
import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApi
import com.example.aikospbus.feature_bus_location.data.remote.dto.Bus
import com.example.aikospbus.feature_api_sp_trans.remote.models.Corredor
import com.example.aikospbus.feature_api_sp_trans.remote.models.Parada
import com.example.aikospbus.feature_api_sp_trans.remote.models.PrevisaoChegada
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        setButtonsClickListeners()

        binding.auth.setOnClickListener {
            lifecycleScope.launch {
                authentication()
            }
        }

        binding.nav.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_mapsFragment)
           getPrevisaoChegada()
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideSoftKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = requireActivity().currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun authentication() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    SPTransApi.retrofitService.authentication("604a216ace42329aa7581b9c6056a8a3dc2f574a680411928d5570478ca4c707")
                        .apply {
                            COOKIE = headers().get("Set-Cookie").toString()
                            println("COOKIE: $COOKIE")
                        }

                if (response.isSuccessful) {
                    val result = response.body()
                    println("Login response: $result")
                } else {
                    println("Erro de autenticação: ${response.errorBody()?.string()}")
                }
            } catch (e: HttpException) {
                println("Erro HTTP: ${e.message()}")
            } catch (e: Exception) {
                println("Erro: ${e.message}")
            }
        }
    }


    private fun getlinhas() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = SPTransApi.retrofitService.getLine(COOKIE, "lapa")
            response.forEach { line ->
                println("Linha: ${line}")
            }
        }
    }

    private fun getloc() {
        CoroutineScope(Dispatchers.IO).launch {
            val response: Bus = SPTransApi.retrofitService.getLinePosition(COOKIE, 841)
            response.veiculos.forEach { veiculo ->
                println("Veiculo Prefixo: ${veiculo.prefixo}")
                println("Localizacao: (${veiculo.latitude}, ${veiculo.longitude})")
                println("Acessivel: ${veiculo.acessibilidade}")
                println("Horario da Amostra: ${veiculo.horarioAmostra}")
            }
        }
    }

    private fun getParada() {
        CoroutineScope(Dispatchers.IO).launch {
            val response: List<Parada> = SPTransApi.retrofitService.getStops(COOKIE, "afonso")
            response.forEach { parada ->
                println("Codigo da Parada: ${parada.codigoParada}")
                println("Nome da Parada: ${parada.nomeParada}")
                println("Endereco: ${parada.enderecoParada}")
                println("Localizacao: (${parada.latitude}, ${parada.longitude})")
            }
        }
    }

    private fun getCorridor() {
        CoroutineScope(Dispatchers.IO).launch {
            val response: List<Corredor> = SPTransApi.retrofitService.getCorredores(COOKIE)
            response.forEach { corredor ->
                println("Codigo do Corredor: ${corredor.codigoCorredor}")
                println("Nome do Corredor: ${corredor.nomeCorredor}")
            }
        }
    }

    private fun getPrevisaoChegada() {
        CoroutineScope(Dispatchers.IO).launch {
            val previsao: PrevisaoChegada = SPTransApi.retrofitService.getPrevisaoChegada(COOKIE, 340015333, 2506)

            println("Linha: ${previsao.codigoLinha}")
            println("Horário Previsto: ${previsao.horarioPrevisto}")
        }
    }


    private fun setButtonsClickListeners() {
        with(binding) {
            busLocationBt.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_busLocationFragment)
//                getloc()
            }

            busLinesBt.setOnClickListener {
//                findNavController().navigate(R.id.action_FirstFragment_to_busLinesFragment)
                getlinhas()
            }

            busStopsBt.setOnClickListener {
//                findNavController().navigate(R.id.action_FirstFragment_to_busStopsFragment)
                getParada()
            }

            busCorridorBt.setOnClickListener {
//                findNavController().navigate(R.id.action_FirstFragment_to_busCorridorFragment)
                getCorridor()
            }
        }
    }

    companion object {
        lateinit var COOKIE: String
    }
}