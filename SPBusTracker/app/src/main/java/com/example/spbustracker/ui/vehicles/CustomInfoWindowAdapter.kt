import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.spbustracker.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {

    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null)

        val titleTextView = view.findViewById<TextView>(R.id.title)
        val originTextView = view.findViewById<TextView>(R.id.origin)
        val destinationTextView = view.findViewById<TextView>(R.id.destination)

        titleTextView.text = marker.title
        val snippet = marker.snippet?.split("\n")
        originTextView.text = snippet?.getOrNull(0)
        destinationTextView.text = snippet?.getOrNull(1)
        return view
    }

}
