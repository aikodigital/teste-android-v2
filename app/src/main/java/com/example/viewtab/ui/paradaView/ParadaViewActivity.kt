package com.example.viewtab.ui.paradaView

import android.R
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewtab.databinding.ActivityParadaBinding
import com.example.viewtab.network.model.Parada
import com.example.viewtab.network.model.Previsao
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.modelNerwork.Status
import com.example.viewtab.ui.paradaView.adapter.AdapterPrevision

class ParadaViewActivity : AppCompatActivity() {

    private var binding: ActivityParadaBinding? = null

    private var mParadaViewViewModel: ParadaViewViewModel? = null

    private var mParadaSelect:Parada? = null

    companion object {
        const val PARAM_PARADA = "PARAM_PARADA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParadaBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mParadaViewViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[ParadaViewViewModel::class.java]

        mParadaSelect =
            if (Build.VERSION.SDK_INT >= 33) {
                intent.getSerializableExtra(PARAM_PARADA, Parada::class.java)
            } else {
                intent.getSerializableExtra(PARAM_PARADA) as? Parada?
            }

        binding?.textName?.text = mParadaSelect?.nomeParada
        binding?.textDescricao?.text = mParadaSelect?.endereço

        setListener()
        mParadaSelect?.apply {
            mParadaViewViewModel?.loadRepositoriesList(paradaCode)
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

        mParadaViewViewModel?.mItems?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, i: Int) {
                val resource: Resource<Previsao?>? = mParadaViewViewModel?.mItems?.get()
                //mHomeViewModel?.dataLoading.set(resource?.status === Status.LOADING)

                if (resource?.status === Status.SUCCESS) {
                    setParadasMap(resource.data)
                } else if (resource?.status === Status.ERROR) {
                    /*DialogUtils.showDialog(
                        getApplicationContext(),
                        resource.message.header,
                        resource.message.body
                    )*/
                }
            }
        })
    }

    private fun setParadasMap(data: Previsao?) {
       // mBinding?.resultList?.isVisible = true
        if (data!=null){
            val adapter = AdapterPrevision()

            binding?.resultList?.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(context)
            }

            adapter.submitList(ArrayList(data.p.linhas)) {
                //ScreenManager.toGoParadaView(context, it)
            }
        }
    }
}