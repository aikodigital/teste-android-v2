package com.example.viewtab.ui.posicao

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewtab.databinding.FragmentDashboardBinding
import com.example.viewtab.databinding.FragmentMapsSeaschBinding
import com.example.viewtab.network.model.Parada
import com.example.viewtab.network.model.Posicao
import com.example.viewtab.network.model.Veiculo
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.modelNerwork.Status
import com.example.viewtab.ui.BaseMapFragment
import com.example.viewtab.ui.paradas.ParadasViewModel
import com.example.viewtab.ui.paradas.adapter.AdapterSearchParadas
import com.example.viewtab.util.ScreenManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class PosicaoFragment : BaseMapFragment() {

    private var mBinding: FragmentMapsSeaschBinding? = null

    private var mViewModel: PosicaoViewModel? = null

    private var hashMap = HashMap<LatLng, Veiculo>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[PosicaoViewModel::class.java]

        mBinding = FragmentMapsSeaschBinding.inflate(inflater, container, false)
        val root: View = mBinding!!.root

        setListener()
        return root
    }

    private fun setListener() {

        onInfoWindowClickListener = GoogleMap.OnInfoWindowClickListener { marker ->
            val item = hashMap[marker.position]
            item?.apply {
                //ScreenManager.toGoParadaView(context,this)
            }
        }

        mViewModel?.mItemsMaps?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, i: Int) {
                val resource: Resource<Posicao?>? = mViewModel?.mItemsMaps?.get()

                if (resource?.status === Status.SUCCESS) {
                    setParadasMap(resource.data)
                } else if (resource?.status === Status.ERROR) {

                }
            }
        })


        mBinding?.searchInputLayout?.isVisible = false
    }


    private fun setParadasMap(data: Posicao?) {
        data?.linha?.forEach{ linha ->
            linha.veiculosList.forEach {
                val posision = LatLng(it.latitude , it.longitude )
                val marker = MarkerOptions()
                    .position(posision)
                    .title("${linha.nameSecundaryByPrimary?: "-"}, ${linha.nameSecundaryByPrimary?:"-"}")

                it.apply {

                    hashMap[posision] = this
                }

                mMap?.addMarker(marker)
            }

        }
    }

    override fun loadLocationResponse(location: Location?) {
        myLocation = location
        mViewModel?.loadRepositoriesList()
        mMap?.apply { onMapReady(this) }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        if (mMap==null){
            googleMap.setOnInfoWindowClickListener(onInfoWindowClickListener)
        }

        mMap = googleMap
        if (myLocation == null ) return
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(myLocation?.latitude ?: 0.0, myLocation?.longitude ?: 0.0)
        mMap?.addMarker(MarkerOptions().position(sydney).title("minha localização"))
        setFocoCamera(sydney)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}