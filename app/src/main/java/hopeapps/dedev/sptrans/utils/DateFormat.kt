package hopeapps.dedev.sptrans.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormat {
    fun String.formattedDate(): String {
        val zonedDateTime = ZonedDateTime.parse(this)
        val formattedDate = zonedDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm", Locale.getDefault()))
        return formattedDate
    }
}