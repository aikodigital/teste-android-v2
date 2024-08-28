package br.com.aiko.projetoolhovivo.ui.line.adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import br.com.aiko.projetoolhovivo.data.model.position.PositionLine
import br.com.aiko.projetoolhovivo.databinding.RowTextBinding

class LineDialogAdapter(
    private val dialog: Dialog,
    private val onClick: (PositionLine) -> Unit
) : RecyclerView.Adapter<LineDialogViewHolder>() {
    private var lines = arrayListOf<PositionLine>()
    private var linesFiltered = arrayListOf<PositionLine>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineDialogViewHolder {
        val binding = RowTextBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LineDialogViewHolder(parent.context, binding){
            onClick(it)
            dialog.dismiss()
        }
    }

    override fun onBindViewHolder(holder: LineDialogViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(lines[position])
    }

    override fun getItemCount(): Int = this.lines.size

    fun updateList(lines: List<PositionLine>) {
        this.lines.clear()
        this.lines.addAll(lines)
        this.linesFiltered.clear()
        this.linesFiltered.addAll(this.lines)
        notifyDataSetChanged()
    }

    fun getFilter(): Filter {
        val filter : Filter
        filter = object : Filter(){
            override fun performFiltering(filter: CharSequence): FilterResults {
                val filteredItems = LineDialogFilter.getFilter(filter, linesFiltered)
                val results = FilterResults()
                results.count = filteredItems.size
                results.values = filteredItems
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                lines = results.values as ArrayList<PositionLine>
                notifyDataSetChanged()
            }
        }
        return filter
    }
}