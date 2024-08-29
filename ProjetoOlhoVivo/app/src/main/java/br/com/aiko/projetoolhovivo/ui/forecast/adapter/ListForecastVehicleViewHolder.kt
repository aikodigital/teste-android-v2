package br.com.aiko.projetoolhovivo.ui.forecast.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import br.com.aiko.projetoolhovivo.R
import br.com.aiko.projetoolhovivo.data.model.forecast.ForecastVehicle
import br.com.aiko.projetoolhovivo.databinding.RowListForecastBinding

class ListForecastVehicleViewHolder(
    private val context: Context,
    private val binding: RowListForecastBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vehicle: ForecastVehicle) {
        binding.tvListForecastVehicleName.text = vehicle.prefixVehicle.toString()
        binding.tvListForecastTime.text =
            context.getString(R.string.tv_stop_details_time_forecast).plus(" ")
                .plus(vehicle.forecast)
    }
}