package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.LinesObjectBinding

class LineAdapter(
    private val context: Context,
    private var busVehicle: MutableList<ListItem> = mutableListOf(),
    private val onItemClick: (ListItem) -> Unit
) : RecyclerView.Adapter<LineAdapter.ViewHolder>() {

    class ViewHolder(private val binding: LinesObjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(listItem: ListItem, onItemClick: (ListItem) -> Unit) {
            when (listItem) {
                is ListItem.BusLine -> {
                    binding.busCodeName.text = listItem.vehicle.line.lineSign
                    binding.destiny.text = "Destino: ${listItem.vehicle.line.destination}"
                    binding.origin.text = "Origem: ${listItem.vehicle.line.origin}"
                    binding.quantity.text = "Quantidade: ${listItem.vehicle.line.vehicleCount} veículos"
                }
                is ListItem.Line -> {
                    binding.busCodeName.text = "Código: ${listItem.line.lineCode}"
                    binding.destiny.text = "Destino: ${listItem.line.destinationTowardsPrimary}"
                    binding.origin.text = "Origem: ${listItem.line.destinationTowardsSecondary}"
                    binding.quantity.text = "Letreiro: ${listItem.line.lineNumberPrefix}-${listItem.line.lineNumberSuffix}"
                }
            }

            binding.root.setOnClickListener { onItemClick(listItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LinesObjectBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vehicle = busVehicle[position]
        holder.bind(vehicle, onItemClick)
    }

    override fun getItemCount(): Int = busVehicle.size

    fun setData(newList: List<ListItem>) {
        val diffCallback = LineDiffCallback(busVehicle, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        busVehicle.clear()
        busVehicle.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}

class LineDiffCallback(
    private val oldList: List<ListItem>,
    private val newList: List<ListItem>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}