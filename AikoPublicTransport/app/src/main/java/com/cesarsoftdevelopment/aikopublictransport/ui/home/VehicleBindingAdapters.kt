package com.cesarsoftdevelopment.aikopublictransport.ui.home

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

@SuppressLint("SetTextI18n")
@BindingAdapter("busPrefix")
fun bindName(busPrefixTextView: TextView, busPrefix: String?) {
    busPrefix?.let {
        busPrefixTextView.text = "Veículo $busPrefix"
    }
}

@BindingAdapter("estimatedTime")
fun bindEstimatedTime(estimatedTimeTextView: TextView, estimatedTime: String?) {
    estimatedTime?.let {
        estimatedTimeTextView.text = "Previsão de chegada às $estimatedTime"
    }

}