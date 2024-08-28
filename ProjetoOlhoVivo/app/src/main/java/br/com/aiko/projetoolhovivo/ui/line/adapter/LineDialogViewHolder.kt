package br.com.aiko.projetoolhovivo.ui.line.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import br.com.aiko.projetoolhovivo.R
import br.com.aiko.projetoolhovivo.data.model.position.PositionLine
import br.com.aiko.projetoolhovivo.databinding.RowTextBinding

class LineDialogViewHolder(
    private val context: Context,
    private val binding: RowTextBinding,
    private val onClick: (PositionLine) -> Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bind(line: PositionLine){
        binding.tvTextName.text = context.getString(R.string.til_title_line_list).plus(" ").plus(line.sign)
        binding.tvTextName.setOnClickListener{ onClick(line) }
    }
}