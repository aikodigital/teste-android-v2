package com.example.aikospbus.feature_bus_corridor.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aikospbus.databinding.CorridorItemBinding
import com.example.aikospbus.feature_bus_corridor.domain.model.BusCorridorModel

class BusCorridorAdapter(private val corridorList: ArrayList<BusCorridorModel>) :
    RecyclerView.Adapter<BusCorridorAdapter.BusCorridorViewHolder>() {

    private lateinit var clickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    class BusCorridorViewHolder(
        val binding: CorridorItemBinding,
        listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BusCorridorViewHolder {
        val view = CorridorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BusCorridorViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: BusCorridorViewHolder, position: Int) {
        val newCorridorList = corridorList[position]

        holder.binding.busCorridorName.text = newCorridorList.nomeCorredor
    }

    override fun getItemCount() = corridorList.size


}