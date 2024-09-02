package com.example.testeaiko.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testeaiko.databinding.ArrivalsVehicleCardBinding
import com.example.testeaiko.datamodels.ModelVeiculo

class VehicleAdapter(private val vehicles: List<ModelVeiculo>,
                     private val onItemClick: (ModelVeiculo) -> Unit) :
    RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val binding =
            ArrivalsVehicleCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.bind(vehicle, onItemClick)
    }

    override fun getItemCount(): Int = vehicles.size

    class VehicleViewHolder(private val binding: ArrivalsVehicleCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(vehicle: ModelVeiculo, onItemClick: (ModelVeiculo) -> Unit) {
            binding.prefix = vehicle.p.toString()
            binding.arrivalTime = vehicle.arrivalTimeFormatted
            binding.accessible = vehicle.a
            binding.executePendingBindings()
            itemView.setOnClickListener {
                onItemClick(vehicle)
            }
        }
    }
}

