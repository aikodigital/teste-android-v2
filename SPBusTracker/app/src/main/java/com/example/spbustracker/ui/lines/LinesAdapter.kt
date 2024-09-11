package com.example.spbustracker.ui.lines

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spbustracker.R
import com.example.spbustracker.model.Line

class LinesAdapter(
    private val context: Context,
    private var lines: List<Line>,
    private val onHeartClicked: (Line) -> Unit
) : RecyclerView.Adapter<LinesAdapter.LineViewHolder>() {

    fun submitList(newLines: List<Line>) {
        lines = newLines
        notifyDataSetChanged()
    }

    private val sharedPreferences = context.getSharedPreferences("Favorites", Context.MODE_PRIVATE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_line, parent, false)
        return LineViewHolder(view)
    }

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        val line = lines[position]
        holder.bind(line)
    }

    override fun getItemCount(): Int = lines.size

    inner class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.lineTitle)
        private val origin: TextView = itemView.findViewById(R.id.lineOrigin)
        private val destination: TextView = itemView.findViewById(R.id.lineDestination)
        private val heartIcon: ImageView = itemView.findViewById(R.id.heartIcon)

        fun bind(line: Line) {
            title.text = "Linha: ${line.c}"
            origin.text = "Origem: ${line.lt1}"
            destination.text = "Destino: ${line.lt0}"

            val isFavorite = sharedPreferences.getBoolean(line.c, false)
            heartIcon.setImageResource(
                if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
            )

            heartIcon.setOnClickListener {
                val newFavoriteState = !isFavorite
                heartIcon.setImageResource(
                    if (newFavoriteState) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
                )
                sharedPreferences.edit().putBoolean(line.c, newFavoriteState).apply()
                onHeartClicked(line)
            }
        }
    }

}
