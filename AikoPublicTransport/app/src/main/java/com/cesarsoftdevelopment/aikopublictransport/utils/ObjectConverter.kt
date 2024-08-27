package com.cesarsoftdevelopment.aikopublictransport.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.cesarsoftdevelopment.aikopublictransport.R
import com.google.android.gms.maps.model.BitmapDescriptorFactory

object ObjectConverter {

    fun getBitmapFromVectorDrawable(context: Context, @DrawableRes drawableId: Int): Bitmap {
        val vectorDrawable = ContextCompat.getDrawable(context, drawableId)!!
        vectorDrawable.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return bitmap
    }



}