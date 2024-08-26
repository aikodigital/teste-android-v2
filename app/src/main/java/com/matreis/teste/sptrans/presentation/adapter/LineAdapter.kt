package com.matreis.teste.sptrans.presentation.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.databinding.ItemLineBinding
import com.matreis.teste.sptrans.domain.model.Line
import com.matreis.teste.sptrans.helper.getDirection

class LineAdapter(
    private val isFavoriteScreen: Boolean
): RecyclerView.Adapter<LineAdapter.MyLineViewHolder>() {

    var onSeeOnMapClick: ((Line) -> Unit) = {}
    var onDeleteFromFavoriteClick: ((Line) -> Unit) = {}
    var onFavoriteClick: ((Line) -> Unit) = {}
    var onSeeBusStopsClick: ((Line) -> Unit) = {}

    private val lines = mutableListOf<Line>()
    fun submitList(lines: List<Line>) {
        this.lines.clear()
        this.lines.addAll(lines)
        notifyDataSetChanged()
    }

    inner class MyLineViewHolder(private val binding: ItemLineBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(line: Line) {
            val lineNumber = "${line.firstNumericSign} - ${line.secondNumericSign}"
            val lineName = line.getDirection()
            binding.apply {
                tvLineName.text = lineName
                tvLineNumber.text = lineNumber
                binding.btnMore.setOnClickListener {
                    createPopupMenu(it)
                }
                binding.clRoot.setOnClickListener {
                    onSeeOnMapClick(line)
                }
            }

        }

        private fun createPopupMenu(anchorView: View) {
            val popupMenu = PopupMenu(binding.root.context, anchorView, Gravity.END)
            val menuId = if (isFavoriteScreen) R.menu.menu_favorite_line else R.menu.lines_menu
            popupMenu.menuInflater.inflate(menuId, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId) {
                    R.id.menuAddFavorite -> onFavoriteClick(lines[adapterPosition])
                    R.id.menuDeleteFavorite -> onDeleteFromFavoriteClick(lines[adapterPosition])
                }
                true
            }
            popupMenu.show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLineViewHolder {
        val binding = ItemLineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyLineViewHolder(binding)
    }

    override fun getItemCount(): Int = lines.size

    override fun onBindViewHolder(holder: MyLineViewHolder, position: Int) {
        holder.bind(lines[position])
    }

}