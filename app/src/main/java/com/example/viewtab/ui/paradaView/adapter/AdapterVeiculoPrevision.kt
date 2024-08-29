package com.example.viewtab.ui.paradaView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.viewtab.R
import com.example.viewtab.databinding.ItemParadaBinding
import com.example.viewtab.databinding.ItemPrevisionBinding
import com.example.viewtab.databinding.ItemPrevisionVeiculoBinding
import com.example.viewtab.network.model.LinhasPrevisao
import com.example.viewtab.network.model.Parada
import com.example.viewtab.network.model.Veiculo

class AdapterVeiculoPrevision:
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mListModel : ArrayList<Veiculo?> = arrayListOf()

    private var mOnClick : ((Veiculo) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemPrevisionVeiculoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_prevision_veiculo,
            parent,
            false
        )

        return PrevisionVeiculoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PrevisionVeiculoViewHolder -> {
                holder.bind(mListModel.getOrNull(position), mOnClick)
            }
        }
    }

    override fun getItemCount(): Int {
        return mListModel.size
    }

    fun submitList(list: ArrayList<Veiculo?>, onClick: ((Veiculo) -> Unit)?) {
        mListModel = list
        mOnClick = onClick
        notifyDataSetChanged()
    }
}