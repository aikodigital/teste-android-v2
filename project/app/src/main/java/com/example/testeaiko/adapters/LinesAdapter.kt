package com.example.testeaiko.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testeaiko.databinding.LineSearchResultCardBinding
import com.example.testeaiko.datamodels.ModelLinha

class LinesAdapter(private val lines: List<ModelLinha>) :
    RecyclerView.Adapter<LinesAdapter.LinesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinesViewHolder {
        val binding =
            LineSearchResultCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LinesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LinesViewHolder, position: Int) {
        val line = lines[position]
        holder.bind(line)
    }

    override fun getItemCount(): Int = lines.size

    class LinesViewHolder(private val binding: LineSearchResultCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(line: ModelLinha) {
            binding.line = "Linha: ${line.cl}"
            binding.sign = "| Letreiro: ${line.lt}-${line.tl}"
            binding.terminal1 = line.tp
            binding.terminal2 = line.ts
            binding.direction = line.sl == 1
            binding.isCircular = line.lc
            binding.executePendingBindings()
        }
    }
}

