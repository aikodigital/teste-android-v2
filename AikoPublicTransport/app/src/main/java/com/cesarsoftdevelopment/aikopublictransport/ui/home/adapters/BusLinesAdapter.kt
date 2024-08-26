package com.cesarsoftdevelopment.aikopublictransport.ui.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import com.cesarsoftdevelopment.aikopublictransport.databinding.BusLineItemBinding
import com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel.BusLinesViewModel
import com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel.HomeViewModel

class BusLinesAdapter(private val busLinesViewModel: BusLinesViewModel) : ListAdapter<BusLineItem, BusLinesAdapter.ViewHolder>(BusLinesDiffCallback())  {

    private var selectedPosition: Int = -1

    class BusLinesDiffCallback : DiffUtil.ItemCallback<BusLineItem>() {
        override fun areItemsTheSame(oldItem: BusLineItem, newItem: BusLineItem): Boolean {
            return oldItem.lineCode == newItem.lineCode
        }
        override fun areContentsTheSame(oldItem: BusLineItem, newItem: BusLineItem): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position == selectedPosition)

        holder.binding.radiobuttonBusLine.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition

            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
            busLinesViewModel.setSelectedLineCode(getItem(position).lineCode)
        }

    }


    class ViewHolder private constructor(
        val binding: BusLineItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item : BusLineItem, isSelected: Boolean) {
            binding.busLine = item
            binding.radiobuttonBusLine.isChecked = isSelected
            binding.executePendingBindings()

        }

        companion object {
            fun from(
                parent: ViewGroup
            ): ViewHolder {
                val binding = BusLineItemBinding.inflate(
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