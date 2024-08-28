package br.com.aiko.projetoolhovivo.ui.line.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import br.com.aiko.projetoolhovivo.R
import br.com.aiko.projetoolhovivo.data.model.line.Line
import br.com.aiko.projetoolhovivo.databinding.RowListLineBinding

class ListLineViewHolder(
    private val context: Context,
    private val binding: RowListLineBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(line: Line) {
        binding.tvListTitleLine.text =
            context.getString(R.string.til_title_line_list).plus(" ").plus(line.firstSign)
                .plus(" - ").plus(line.secondSign.toString())

        var beginLine = ""
        var endLine = ""

        if (line.direction == 1) {
            beginLine = line.terminalPrimaryBySecondary
            endLine = line.terminalSecondaryByPrimary
        } else if (line.direction == 2) {
            beginLine = line.terminalSecondaryByPrimary
            endLine = line.terminalPrimaryBySecondary
        }

        binding.tvListBeginLine.text =
            context.getString(R.string.til_begin_line_list).plus(" ").plus(beginLine)
        binding.tvListEndLine.text =
            context.getString(R.string.til_end_line_list).plus(" ").plus(endLine)
    }
}