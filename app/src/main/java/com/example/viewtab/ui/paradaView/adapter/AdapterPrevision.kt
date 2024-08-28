package com.example.viewtab.ui.paradaView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.viewtab.R
import com.example.viewtab.databinding.ItemParadaBinding
import com.example.viewtab.databinding.ItemPrevisionBinding
import com.example.viewtab.network.model.LinhasPrevisao
import com.example.viewtab.network.model.Parada

class AdapterPrevision:
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mListModel : ArrayList<LinhasPrevisao?> = arrayListOf()

    private var mOnClick : ((LinhasPrevisao) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemPrevisionBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_prevision,
            parent,
            false
        )

        return PrevisionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PrevisionViewHolder -> {
                holder.bind(mListModel.getOrNull(position), mOnClick)
            }
        }
    }

    override fun getItemCount(): Int {
        return mListModel.size
    }

    fun submitList(list: ArrayList<LinhasPrevisao?>, onClick: ((LinhasPrevisao) -> Unit)?) {
        mListModel = list
        mOnClick = onClick
        notifyDataSetChanged()
    }
}