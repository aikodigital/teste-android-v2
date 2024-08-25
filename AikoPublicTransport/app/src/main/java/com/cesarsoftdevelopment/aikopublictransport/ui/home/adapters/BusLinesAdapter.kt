package com.cesarsoftdevelopment.aikopublictransport.ui.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import com.cesarsoftdevelopment.aikopublictransport.databinding.BusLineItemBinding

class BusLinesAdapter : ListAdapter<BusLineItem, BusLinesAdapter.ViewHolder>(BusLinesDiffCallback())  {
    class BusLinesDiffCallback : DiffUtil.ItemCallback<BusLineItem>() {
        override fun areItemsTheSame(oldItem: BusLineItem, newItem: BusLineItem): Boolean {
            return oldItem.lineCode == newItem.lineCode
        }
        override fun areContentsTheSame(oldItem: BusLineItem, newItem: BusLineItem): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ViewHolder private constructor(
        private val binding: BusLineItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item : BusLineItem) {
            binding.busLine = item
            binding.executePendingBindings()

//            binding.imageButtonEdit.setOnClickListener { view ->
//                view.findNavController().navigate(
//                    PetListFragmentDirections.actionPetListFragmentToPetEditFragment(pet)
//                )
//            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = BusLineItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }
    }


}