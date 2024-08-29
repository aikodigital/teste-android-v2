package com.example.viewtab.ui.paradaView.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.viewtab.databinding.ItemParadaBinding
import com.example.viewtab.databinding.ItemPrevisionBinding
import com.example.viewtab.databinding.ItemPrevisionVeiculoBinding
import com.example.viewtab.network.model.LinhasPrevisao
import com.example.viewtab.network.model.Parada
import com.example.viewtab.network.model.Veiculo
import java.text.SimpleDateFormat
import java.util.*

class PrevisionVeiculoViewHolder (val binding: ItemPrevisionVeiculoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Veiculo?, onClick: ((Veiculo) -> Unit)?) {

        binding.apply {
            model?.previsaoHorario?.apply {
                textName.text = formatDateToTime(this)
            }

            root.setOnClickListener {
                model?.apply {
                    onClick?.invoke(this)
                }
            }
        }

    }

    fun formatDateToTime(dateString: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")
            val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date: Date? = inputFormat.parse(dateString)

            if (date != null) {
                outputFormat.format(date)
            } else {
                "--:--"
            }
        }catch (e: Exception){
            dateString
        }

    }
}