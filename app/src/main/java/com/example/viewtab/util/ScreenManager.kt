package com.example.viewtab.util

import android.content.Context
import android.content.Intent
import com.example.viewtab.network.model.Linha
import com.example.viewtab.network.model.Parada
import com.example.viewtab.ui.linhasDetalhe.LinhaDetalheActivity
import com.example.viewtab.ui.paradaView.ParadaViewActivity

object ScreenManager {

    fun toGoParadaView(context:Context?,parada: Parada){
        val intent = Intent(context, ParadaViewActivity::class.java)
        intent.putExtra(ParadaViewActivity.PARAM_PARADA, parada)
        context?.startActivity(intent)
    }

    fun toGoLinhaDetalhe(context:Context?, linha: Linha){
        val intent = Intent(context, LinhaDetalheActivity::class.java)
        intent.putExtra(LinhaDetalheActivity.PARAM_LINHA, linha)
        context?.startActivity(intent)
    }
}
