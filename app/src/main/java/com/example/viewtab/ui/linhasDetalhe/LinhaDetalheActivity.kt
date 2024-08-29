package com.example.viewtab.ui.linhasDetalhe

import android.R
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewtab.databinding.ActivityParadaBinding
import com.example.viewtab.network.model.Linha
import com.example.viewtab.network.model.Parada
import com.example.viewtab.network.model.Previsao
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.modelNerwork.Status
import com.example.viewtab.ui.paradaView.adapter.AdapterPrevision
import com.example.viewtab.ui.paradas.adapter.AdapterSearchParadas

class LinhaDetalheActivity : AppCompatActivity() {

    private var binding: ActivityParadaBinding? = null

    private var mLinhaDetalheViewModel: LinhaDetalheViewModel? = null

    private var mParadaSelect:Linha? = null

    companion object {
        const val PARAM_LINHA = "PARAM_LINHA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParadaBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mLinhaDetalheViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[LinhaDetalheViewModel::class.java]

        mParadaSelect =
            if (Build.VERSION.SDK_INT >= 33) {
                intent.getSerializableExtra(PARAM_LINHA, Linha::class.java)
            } else {
                intent.getSerializableExtra(PARAM_LINHA) as? Linha?
            }

        binding?.textName?.text = mParadaSelect?.namePrimaryBySecundary + mParadaSelect?.nameSecundaryByPrimary
        binding?.textDescricao?.isVisible = false

        setListener()
        mParadaSelect?.apply {
            mLinhaDetalheViewModel?.loadRepositoriesList(linhaCode)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setListener() {

        mLinhaDetalheViewModel?.mItems?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, i: Int) {
                val resource: Resource<List<Parada?>?>? = mLinhaDetalheViewModel?.mItems?.get()
                //mHomeViewModel?.dataLoading.set(resource?.status === Status.LOADING)

                if (resource?.status === Status.SUCCESS) {
                    setParadasMap(resource.data)
                } else if (resource?.status === Status.ERROR) {

                }
            }
        })
    }

    private fun setParadasMap(data: List<Parada?>?) {
       // mBinding?.resultList?.isVisible = true
        if (data!=null){
            val adapter = AdapterSearchParadas()

            binding?.resultList?.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(context)
            }

            adapter.submitList(ArrayList(data),{}) {
                //ScreenManager.toGoParadaView(context, it)
            }
        }
    }
}