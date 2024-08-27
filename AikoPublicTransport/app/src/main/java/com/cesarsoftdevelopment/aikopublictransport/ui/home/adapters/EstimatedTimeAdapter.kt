package com.cesarsoftdevelopment.aikopublictransport.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import com.cesarsoftdevelopment.aikopublictransport.data.model.VehicleItem
import com.cesarsoftdevelopment.aikopublictransport.databinding.EstimatedArrivalTimeItemBinding

class EstimatedTimeAdapter : ListAdapter<VehicleItem, EstimatedTimeAdapter.ViewHolder>(EstimatedTimeDiffCallback()) {
    class EstimatedTimeDiffCallback : DiffUtil.ItemCallback<VehicleItem>() {
        override fun areItemsTheSame(oldItem: VehicleItem, newItem: VehicleItem): Boolean {
            return oldItem.vehiclePrefix == newItem.vehiclePrefix
        }
        override fun areContentsTheSame(oldItem: VehicleItem, newItem: VehicleItem): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }


    class ViewHolder private constructor(
        val binding: EstimatedArrivalTimeItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item : VehicleItem) {
            binding.vehicle = item
            binding.executePendingBindings()

        }

        companion object {
            fun from(
                parent: ViewGroup
            ): ViewHolder {
                val binding = EstimatedArrivalTimeItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(
                    binding
                )
            }
        }
    }



}