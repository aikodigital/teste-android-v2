package com.example.spbustracker

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.spbustracker.network.SPTransApiService
import com.example.spbustracker.repository.AutenticacaoRepository
import com.example.spbustracker.model.AutenticacaoViewModel
import com.example.spbustracker.viewmodel.AutenticacaoViewModelFactory

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var autenticacaoViewModel: AutenticacaoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val token = getString(R.string.sptrans_token)

        val repository = AutenticacaoRepository(
            SPTransApiService.create(token, this, addInterceptor = false)
        )
        val factory = AutenticacaoViewModelFactory(repository)
        autenticacaoViewModel = ViewModelProvider(this, factory)[AutenticacaoViewModel::class.java]

        autenticacaoViewModel.autenticar(token)

        autenticacaoViewModel.autenticado.observe(this) { autenticado ->
            if (autenticado) {
                showAuthenticationModal(
                    "Autenticado com sucesso na API do SPTrans"
                ) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            } else {
                showAuthenticationModal(
                    "Falha na autenticação com a API do SPTrans"
                )
            }
        }
    }

    private fun showAuthenticationModal(
        message: String,
        onDismiss: (() -> Unit)? = null
    ) {
        AlertDialog.Builder(this)
            .setTitle("Autenticação")
            .setMessage(message)
            .setIcon(android.R.drawable.ic_menu_info_details)
            .setPositiveButton("OK") { _, _ -> onDismiss?.invoke() }
            .show()
    }

}
