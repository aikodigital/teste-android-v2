package br.com.aiko.projetoolhovivo.ui.line.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.aiko.projetoolhovivo.data.model.line.Line
import br.com.aiko.projetoolhovivo.databinding.RowListLineBinding

class ListLineAdapter(
    private val onClick: (Line) -> Unit
) : RecyclerView.Adapter<ListLineViewHolder>() {
    private var lines = arrayListOf<Line>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListLineViewHolder {
        val binding = RowListLineBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListLineViewHolder(parent.context, binding, onClick)
    }

    override fun onBindViewHolder(holder: ListLineViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(lines[position])
    }

    override fun getItemCount(): Int = this.lines.size

    fun updateList(lines: List<Line>) {
        this.lines.clear()
        this.lines.addAll(lines)
        notifyDataSetChanged()
    }
}