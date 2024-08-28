package com.example.viewtab.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.viewtab.R
import com.example.viewtab.databinding.ItemLinhaBinding
import com.example.viewtab.databinding.ItemParadaBinding
import com.example.viewtab.network.model.Linha
import com.example.viewtab.network.model.Parada

class AdapterSearchLinha:
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mListModel : ArrayList<Linha?> = arrayListOf()

    private var mOnClick : ((Linha) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemLinhaBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_linha,
            parent,
            false
        )

        return LinhaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LinhaViewHolder -> {
                holder.bind(mListModel.getOrNull(position), mOnClick)
            }
        }
    }

    override fun getItemCount(): Int {
        return mListModel.size
    }

    fun submitList(list: ArrayList<Linha?>, onClick: ((Linha) -> Unit)?) {
        mListModel = list
        mOnClick = onClick
        notifyDataSetChanged()
    }
}