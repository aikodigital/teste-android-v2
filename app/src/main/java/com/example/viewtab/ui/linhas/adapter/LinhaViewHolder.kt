package com.example.viewtab.ui.linhas.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.viewtab.databinding.ItemLinhaBinding
import com.example.viewtab.network.model.Linha

class LinhaViewHolder (val binding: ItemLinhaBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Linha?, onClick: ((Linha) -> Unit)?) {

        binding.apply {
            this.textName.text = "${model?.linhaCode?:""}-${model?.namePrimaryBySecundary?:""}"

            this.textDescricao.text = model?.nameSecundaryByPrimary?:""

            root.setOnClickListener {
                model?.apply {
                    onClick?.invoke(this)
                }
            }
        }

    }

}