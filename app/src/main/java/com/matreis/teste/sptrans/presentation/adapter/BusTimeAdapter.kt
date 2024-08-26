package com.matreis.teste.sptrans.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matreis.teste.sptrans.databinding.ItemBusTimeBinding
import com.matreis.teste.sptrans.domain.model.Vehicle

class BusTimeAdapter(
    private val onItemClicked: (Vehicle) -> Unit
): RecyclerView.Adapter<BusTimeAdapter.MyBusTimeViewHolder>() {

    private val vehicles = mutableListOf<Vehicle>()
    fun submitList(vehicles: List<Vehicle>) {
        this.vehicles.clear()
        this.vehicles.addAll(vehicles)
        notifyDataSetChanged()
    }

    inner class MyBusTimeViewHolder(private val binding: ItemBusTimeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(vehicle: Vehicle) {
            val busInfo = "${vehicle.prefix} às ${vehicle.estimatedArrivalTime}"
            binding.tvBusTime.text = busInfo
            binding.root.setOnClickListener {
                onItemClicked(vehicle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBusTimeViewHolder {
        val binding = ItemBusTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyBusTimeViewHolder(binding)
    }

    override fun getItemCount(): Int = vehicles.size

    override fun onBindViewHolder(holder: MyBusTimeViewHolder, position: Int) {
        holder.bind(vehicles[position])
    }

}