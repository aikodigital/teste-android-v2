package com.example.aikospbus.custom_components

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.aikospbus.R

class MenuUtil (){

    fun showMenu(constraintLayout: ConstraintLayout) {
        constraintLayout.visibility = View.VISIBLE
    }

    fun hideMenu(constraintLayout: ConstraintLayout) {
        constraintLayout.visibility = View.GONE
    }

    fun setClickListener(constraintLayout: ConstraintLayout, imageView: ImageView) {
        constraintLayout.setOnClickListener {
            changeEyeVisibility(imageView)
        }
    }

    private fun changeEyeVisibility(imageView: ImageView) {
        val currentTag = imageView.tag
        if (currentTag == "eye_closed") {
            imageView.setImageResource(R.drawable.eye_open)
            imageView.tag = "eye_open"
        } else {
            imageView.setImageResource(R.drawable.eye_closed)
            imageView.tag = "eye_closed"
        }
    }

}