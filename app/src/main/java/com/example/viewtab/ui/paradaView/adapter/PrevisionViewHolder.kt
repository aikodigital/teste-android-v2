package com.example.viewtab.ui.paradaView.adapter

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viewtab.databinding.ItemPrevisionBinding
import com.example.viewtab.network.model.LinhasPrevisao


class PrevisionViewHolder (val binding: ItemPrevisionBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: LinhasPrevisao?, onClick: ((LinhasPrevisao) -> Unit)?) {

        binding.apply {
            this.textName.text = "${model?.letriroCompleto?:""}-${model?.namePrimaryBySecundary?:""}/${model?.nameSecundaryByPrimary?:""}"

            val adapter = AdapterVeiculoPrevision()

            binding.veiculor?.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            }

            model?.veiculosList?.let {
                adapter.submitList(ArrayList(it)) {
                    //ScreenManager.toGoParadaView(context, it)
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