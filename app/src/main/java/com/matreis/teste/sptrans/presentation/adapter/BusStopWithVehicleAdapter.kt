package com.matreis.teste.sptrans.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matreis.teste.sptrans.databinding.ItemBusStopBinding
import com.matreis.teste.sptrans.domain.model.BusStop
import com.matreis.teste.sptrans.domain.model.Vehicle

class BusStopWithVehicleAdapter(): RecyclerView.Adapter<BusStopWithVehicleAdapter.MyBusStopWithVehicle>() {

    var onVehicleClick: ((Vehicle) -> Unit) = {}
    private var busStops: MutableList<BusStop> = mutableListOf()
    fun submitList(busStops: List<BusStop>) {
        this.busStops.clear()
        this.busStops.addAll(busStops)
        notifyDataSetChanged()
    }

    inner class MyBusStopWithVehicle(private val itemBusStopBinding: ItemBusStopBinding): RecyclerView.ViewHolder(itemBusStopBinding.root) {
        fun bind(busStop: BusStop) {
            itemBusStopBinding.tvStopName.text = busStop.name
            itemBusStopBinding.tvStopAddress.text = busStop.address
            val busTimeAdapter = BusTimeAdapter {
                onVehicleClick(it)
            }
            itemBusStopBinding.gridBusTime.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = busTimeAdapter
                setHasFixedSize(true)
            }
            busTimeAdapter.submitList(busStop.vehicles)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBusStopWithVehicle {
        val itemBusStopBinding = ItemBusStopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyBusStopWithVehicle(itemBusStopBinding)
    }

    override fun getItemCount(): Int = busStops.size

    override fun onBindViewHolder(holder: MyBusStopWithVehicle, position: Int) {
        holder.bind(busStops[position])
    }

}