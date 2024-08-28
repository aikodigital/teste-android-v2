package com.example.viewtab.ui.home

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewtab.databinding.FragmentMapsSeaschBinding
import com.example.viewtab.network.model.Parada
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.modelNerwork.Status
import com.example.viewtab.ui.BaseMapFragment
import com.example.viewtab.ui.home.adapter.AdapterSearchParadas
import com.example.viewtab.util.ScreenManager
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ParadasFragment : BaseMapFragment() {

    private var mBinding: FragmentMapsSeaschBinding? = null

    private var mViewModel: ParadasViewModel? = null

    private var hashMap = HashMap<LatLng,Parada>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[ParadasViewModel::class.java]

        mBinding = FragmentMapsSeaschBinding.inflate(inflater, container, false)
        val root: View = mBinding!!.root

        setListener()
        return root
    }

    private fun setListener() {
        // se fez desnessesario pos enviando qualquer localização(em coordenadas) ele retorna as 366 paradas
/*        onCameraIdleListener = GoogleMap.OnCameraIdleListener {
            val cameraPosition = mMap?.cameraPosition
            val centerLatitude = cameraPosition?.target?.latitude ?: 0.0
            val centerLongitude = cameraPosition?.target?.longitude ?: 0.0

            mHomeViewModel?.loadRepositoriesList("$centerLatitude,$centerLongitude")
        }
*/
        onInfoWindowClickListener = GoogleMap.OnInfoWindowClickListener { marker ->
            val item = hashMap[marker.position]
            item?.apply {
                ScreenManager.toGoParadaView(context,this)
            }
        }

        mViewModel?.mItemsMaps?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, i: Int) {
                val resource: Resource<List<Parada?>?>? = mViewModel?.mItemsMaps?.get()

                if (resource?.status === Status.SUCCESS) {
                    setParadasMap(resource.data)
                } else if (resource?.status === Status.ERROR) {

                }
            }
        })

        mViewModel?.mItemsSearch?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, i: Int) {
                val resource: Resource<List<Parada?>?>? = mViewModel?.mItemsSearch?.get()
                //mHomeViewModel?.dataLoading.set(resource?.status === Status.LOADING)

                if (resource?.status === Status.SUCCESS) {
                   setParadasSearch(resource.data)
                } else if (resource?.status === Status.ERROR) {

                }
            }
        })

        mBinding?.searchInputLayout?.setEndIconOnClickListener {
            val searchQuery = mBinding?.searchEditText?.text.toString()
            if (!searchQuery.isNullOrEmpty()) {
                mViewModel?.loadRepositoriesListSearch(searchQuery)
            }
        }
    }

    private fun setParadasSearch(data: List<Parada?>?) {
        mBinding?.resultSearch?.isVisible = true
        mBinding?.resultList?.isVisible = true
        if (!data.isNullOrEmpty()){
            val adapter = AdapterSearchParadas()

            mBinding?.resultList?.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(context)
            }

            adapter.submitList(ArrayList(data),{
                ScreenManager.toGoParadaView(context,it)
            },{
                setFocoCamera(LatLng(it.latitude,it.longitude))
                clearSearch()
            })
        }
    }

    private fun clearSearch(){
        setParadasSearch(ArrayList())
        mBinding?.resultList?.isVisible = false
        mBinding?.resultSearch?.isVisible = false
    }

    private fun setParadasMap(data: List<Parada?>?) {
        data?.forEach{

            val posision = LatLng(it?.latitude ?: 0.0, it?.longitude ?: 0.0)
            val marker = MarkerOptions()
                .position(posision)
                .title("${it?.nomeParada?: "-"}, ${it?.endereço?:"-"}")

            it?.apply {

                hashMap[posision] = this
            }

            mMap?.addMarker(marker)
        }
    }

    override fun loadLocationResponse(location: Location?) {
        myLocation = location
        mViewModel?.loadRepositoriesList("${myLocation?.latitude ?: 0.0},${myLocation?.longitude ?: 0.0}")
        mMap?.apply { onMapReady(this) }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        if (mMap==null){
            googleMap.setOnInfoWindowClickListener(onInfoWindowClickListener)
        }

        mMap = googleMap
        //getCurrentLocation()
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

