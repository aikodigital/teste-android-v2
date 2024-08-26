package com.leonardolino.busfinder.utils

import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

fun resizeBitmapDescriptor(
    drawable: Drawable,
    width: Int,
    height: Int,
    tintColor: Int? = null
): BitmapDescriptor {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = android.graphics.Canvas(bitmap)

    tintColor?.let {
        drawable.mutate()
        drawable.colorFilter = PorterDuffColorFilter(it, PorterDuff.Mode.SRC_IN)
    }

    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}

@Composable
fun getBitmapDescriptorFromResource(
    resourceId: Int,
    width: Int = 100,
    height: Int = 100,
    tintColor: Int? = null
): BitmapDescriptor {
    val context = LocalContext.current
    val drawable = context.getDrawable(resourceId) ?: return BitmapDescriptorFactory.defaultMarker()
    return resizeBitmapDescriptor(drawable, width, height, tintColor)
}