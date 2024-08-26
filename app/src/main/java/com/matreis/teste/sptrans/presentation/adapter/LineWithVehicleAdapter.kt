package com.matreis.teste.sptrans.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matreis.teste.sptrans.databinding.ItemLineWithBusTimeBinding
import com.matreis.teste.sptrans.domain.model.LineWithVehicles
import com.matreis.teste.sptrans.domain.model.Vehicle
import com.matreis.teste.sptrans.helper.getDirection

class LineWithVehicleAdapter(): RecyclerView.Adapter<LineWithVehicleAdapter.MyLineWithVehicleViewHolder>() {

    private var lineSelected: Long = 0L
    private var lines: MutableList<LineWithVehicles> = mutableListOf()
    fun submitList(lines: List<LineWithVehicles>) {
        this.lines.clear()
        this.lines.addAll(lines)
        notifyDataSetChanged()
    }
    fun setLineSelected(lineCode: Long) {
        lineSelected = lineCode
    }

    inner class MyLineWithVehicleViewHolder(val binding: ItemLineWithBusTimeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(line: LineWithVehicles) {
            binding.tvLineSign.text = line.fullSign
            binding.tvLineDescription.text = line.getDirection()
            val busTimeAdapter = BusTimeAdapter {

            }
            busTimeAdapter.submitList(line.vehicles)
            binding.rvBusTime.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = busTimeAdapter
                setHasFixedSize(true)
            }
            if(line.lineCode == lineSelected) {
                binding.viewLineSinalizer.visibility = ViewGroup.VISIBLE
            }else {
                binding.viewLineSinalizer.visibility = ViewGroup.GONE
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLineWithVehicleViewHolder {
        val binding = ItemLineWithBusTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyLineWithVehicleViewHolder(binding)
    }

    override fun getItemCount(): Int = lines.size

    override fun onBindViewHolder(holder: MyLineWithVehicleViewHolder, position: Int) = holder.bind(lines[position])


}