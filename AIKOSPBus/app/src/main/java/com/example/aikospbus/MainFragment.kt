package com.example.aikospbus

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.aikospbus.databinding.FragmentMainBinding
import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApi
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

        binding.auth.setOnClickListener {
            lifecycleScope.launch {
                authentication()
            }
        }

        binding.nav.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_mapsFragment)
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

    private suspend fun authentication() {
        val token = "23af337f5df26f2d9a4df1d7b720dbd1437c92fa25d80af31aca433539128894"
        try {
            val response = SPTransApi.retrofitService.authentication(token)
            val cookie = response.headers()["Set-Cookie"] ?: ""

            println("authentication: $response")

//            val lines = SPTransApi.retrofitService.getLine(cookie, "8000")
//            val position = SPTransApi.retrofitService.getLinePosition(cookie, 14)
        } catch (e: HttpException) {
            Log.e("SPTransApi", "Authentication failed: ${e.message}")
        }
    }
}