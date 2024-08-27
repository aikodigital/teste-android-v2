package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.BusObjectBinding
import com.example.myapplication.model.BusLineVehicle

class BusAdapter(requireContext: Context) :
    ListAdapter<BusLineVehicle, BusAdapter.ViewHolder>(BusDiffCallback()) {

    class ViewHolder(private val binding: BusObjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(busVehicle: BusLineVehicle) {
            with(binding) {
                busCodeName.text = busVehicle.line.lineSign
                destiny.text = busVehicle.line.destination
                busPrefix.text = "Prefixo: ${busVehicle.vehicle.busPrefix}"
                arriveTime.text = busVehicle.vehicle.arriveTime
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BusObjectBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vehicle = getItem(position)
        holder.bind(vehicle)
    }

    private class BusDiffCallback : DiffUtil.ItemCallback<BusLineVehicle>() {
        override fun areItemsTheSame(oldItem: BusLineVehicle, newItem: BusLineVehicle): Boolean {
            // Verifique se há um identificador único para comparar os itens
            return oldItem.vehicle.busPrefix == newItem.vehicle.busPrefix
        }

        override fun areContentsTheSame(oldItem: BusLineVehicle, newItem: BusLineVehicle): Boolean {
            return oldItem == newItem
        }
    }
}