package com.example.aikospbus.feature_bus_stop_prediction.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aikospbus.databinding.StopPredictionItemBinding
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.VeiculosTestDto
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.StopPredictionModel
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.VeiculoTestModel

class StopPredictionAdapter(private val stopPredictionList: ArrayList<VeiculosTestDto>) :
    RecyclerView.Adapter<StopPredictionAdapter.StopPredictionViewHolder>() {

    private lateinit var clickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        clickListener = listener
    }

    class StopPredictionViewHolder(
        val binding: StopPredictionItemBinding,
        lister: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                lister.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StopPredictionViewHolder {
        val view = StopPredictionItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StopPredictionViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(
        holder: StopPredictionViewHolder,
        position: Int
    ) {
        val newStopPredictionList = stopPredictionList[position]

        holder.binding.busNameText.text = newStopPredictionList.prefixo
        holder.binding.busPrevisionValue.text = newStopPredictionList.horarioChegada
        holder.binding.lastAttValue.text = newStopPredictionList.horarioAtualizacao
    }

    override fun getItemCount() = stopPredictionList.size


}