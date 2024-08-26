package com.example.aikospbus.common.custom_components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.aikospbus.R
import com.example.aikospbus.databinding.CustomHeaderBinding

class CustomHeader @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr) {

    private var binding: CustomHeaderBinding =
        CustomHeaderBinding.inflate(LayoutInflater.from(context), this)

    private var tittleText: String? = ""
        set(value) {
            field = value
            binding.fragmentName.text = tittleText ?: ""
        }

    init {
        val atributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomHeader)

        tittleText = atributes.getString(R.styleable.CustomHeader_fragmentName)


        atributes.recycle()
    }

    interface HeaderClickListener {
        fun setBackButtonClickListener()
    }

    fun setConfig(listener: HeaderClickListener? = null, title: String? = null) {
        setListener(listener)
        setTitle(title)
    }

    private fun setListener(listener: HeaderClickListener?) {
        binding.customHeaderArrowIc.setOnClickListener { listener?.setBackButtonClickListener() }
    }

    private fun setTitle(title: String?) {
        this.tittleText = title
    }
}