package com.example.aikospbus.feature_bus_lines.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.aikospbus.databinding.FragmentBusLinesBinding
import com.example.aikospbus.databinding.LinesItemBinding
import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel

class BusLinesAdapter(private val linesList: ArrayList<BusLinesModel>) :
    RecyclerView.Adapter<BusLinesAdapter.BusLinesViewHolder>() {

    private lateinit var clickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    class BusLinesViewHolder(
        val binding: LinesItemBinding,
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
    ): BusLinesViewHolder {
        val view = LinesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BusLinesViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: BusLinesViewHolder, position: Int) {
        val newBusLinesList = linesList[position]

        holder.binding.busSignText.text = newBusLinesList.letreiro
        holder.binding.busTypeText.text = newBusLinesList.tipo.toString()
        holder.binding.busSecondaryTerminalText.text = newBusLinesList.terminalSecundario
        holder.binding.busPrimaryTerminalText.text = newBusLinesList.terminalPrincipal

        if (newBusLinesList.sentido == 1) {
            holder.binding.busDirectionText.text = newBusLinesList.terminalPrincipal
        } else {
            holder.binding.busDirectionText.text = newBusLinesList.terminalSecundario
        }

    }

    override fun getItemCount() = linesList.size


}