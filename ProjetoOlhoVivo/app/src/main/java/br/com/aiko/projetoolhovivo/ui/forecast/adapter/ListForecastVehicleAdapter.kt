package br.com.aiko.projetoolhovivo.ui.forecast.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.aiko.projetoolhovivo.data.model.forecast.ForecastVehicle
import br.com.aiko.projetoolhovivo.databinding.RowListForecastBinding

class ListForecastVehicleAdapter : RecyclerView.Adapter<ListForecastVehicleViewHolder>() {
    private var vehicles = arrayListOf<ForecastVehicle>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListForecastVehicleViewHolder {
        val binding = RowListForecastBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListForecastVehicleViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ListForecastVehicleViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(vehicles[position])
    }

    override fun getItemCount(): Int = this.vehicles.size

    fun updateList(lines: List<ForecastVehicle>) {
        this.vehicles.clear()
        this.vehicles.addAll(lines)
        notifyDataSetChanged()
    }
}