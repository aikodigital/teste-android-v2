package com.example.viewtab.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.viewtab.databinding.ItemParadaBinding
import com.example.viewtab.network.model.Parada

class ParadaViewHolder (val binding: ItemParadaBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Parada?, onClick: ((Parada) -> Unit)?, onClickLocal: ((Parada) -> Unit)?) {

        binding.apply {
            this.textName.text = model?.nomeParada

            this.textDescricao.text = model?.endereço

            this.imageLocalize.setOnClickListener {
                model?.apply {
                    onClickLocal?.invoke(this)
                }
            }

            root.setOnClickListener {
                model?.apply {
                    onClick?.invoke(this)
                }
            }
        }

    }

}