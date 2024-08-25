package com.cesarsoftdevelopment.aikopublictransport.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import com.cesarsoftdevelopment.aikopublictransport.ui.home.adapters.BusLinesAdapter
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource

@SuppressLint("SetTextI18n")
@BindingAdapter("firstLineNumber", "secondLineNumber", "primaryTerminal")
fun bindBusLine(
    busLineTextView: TextView,
    firstLineNumber: String?,
    secondLineNumber: Int,
    primaryTerminal: String?
) {
    firstLineNumber?.let {
        busLineTextView.text = "$firstLineNumber-$secondLineNumber - $primaryTerminal"
    }
}