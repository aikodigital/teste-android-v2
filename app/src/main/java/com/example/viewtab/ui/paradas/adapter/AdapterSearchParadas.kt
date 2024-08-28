package com.example.viewtab.ui.paradas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.viewtab.R
import com.example.viewtab.databinding.ItemParadaBinding
import com.example.viewtab.network.model.Parada

class AdapterSearchParadas:
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mListModel : ArrayList<Parada?> = arrayListOf()

    private var mOnClick : ((Parada) -> Unit)? = null

    private var mOnClickLocal : ((Parada) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemParadaBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_parada,
            parent,
            false
        )

        return ParadaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ParadaViewHolder -> {
                holder.bind(mListModel.getOrNull(position), mOnClick, mOnClickLocal)
            }
        }
    }

    override fun getItemCount(): Int {
        return mListModel.size
    }

    fun submitList(list: ArrayList<Parada?>, onClick: ((Parada) -> Unit)?, onClickLocal: ((Parada) -> Unit)?) {
        mListModel = list
        mOnClick = onClick
        mOnClickLocal = onClickLocal
        notifyDataSetChanged()
    }
}