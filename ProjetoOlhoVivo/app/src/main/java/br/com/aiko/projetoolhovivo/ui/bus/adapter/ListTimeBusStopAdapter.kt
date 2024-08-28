package br.com.aiko.projetoolhovivo.ui.bus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.aiko.projetoolhovivo.R

class ListTimeBusStopAdapter: RecyclerView.Adapter<ListTimeBusStopViewHolder>() {
    private var times = arrayListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTimeBusStopViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_list_coming_bus_in_stop, parent, false)
        return ListTimeBusStopViewHolder(item)
    }

    override fun onBindViewHolder(holder: ListTimeBusStopViewHolder, position: Int) {
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = this.times.size

    fun updateList(qtd: Int){
        this.times.clear()
        for(i in 0..qtd){
            this.times.add(i)
        }
    }
}