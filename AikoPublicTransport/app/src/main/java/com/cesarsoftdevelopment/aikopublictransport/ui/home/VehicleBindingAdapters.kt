package com.cesarsoftdevelopment.aikopublictransport.ui.home

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("busPrefix")
fun bindName(busPrefixTextView: TextView, busPrefix: String?) {
    busPrefix?.let {
        busPrefixTextView.text = busPrefix
    }
}

@BindingAdapter("estimatedTime")
fun bindEstimatedTime(estimatedTimeTextView: TextView, estimatedTime: String?) {
    estimatedTime?.let {
        estimatedTimeTextView.text = estimatedTime
    }

}