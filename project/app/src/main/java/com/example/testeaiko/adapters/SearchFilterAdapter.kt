package com.example.testeaiko.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testeaiko.R

class SearchFilterAdapter(
        private val items: List<String>,
        private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<SearchFilterAdapter.SearchTargetViewHolder>() {

    private var selectedItem = 0

    inner class SearchTargetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.searchTargetTextView)

        fun bind(item: String, position: Int) {
            textView.text = item
            textView.isSelected = position == selectedItem
            textView.setTextColor(if (position == selectedItem) Color.WHITE else Color.LTGRAY)
            textView.setOnClickListener {
                selectItem(position)
            }
        }

        fun selectItem(position: Int) {
            selectedItem = position
            notifyDataSetChanged()
            onItemSelected(items[position])
        }
    }

    fun getSelecteditem(): Int {
        return selectedItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTargetViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_filter_item, parent, false)
        return SearchTargetViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchTargetViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size
}
