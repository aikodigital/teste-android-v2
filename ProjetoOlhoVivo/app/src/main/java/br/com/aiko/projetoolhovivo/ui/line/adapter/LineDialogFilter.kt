package br.com.aiko.projetoolhovivo.ui.line.adapter

import br.com.aiko.projetoolhovivo.data.model.position.PositionLine
import java.text.Normalizer
import java.util.Locale

class LineDialogFilter {
    companion object {
        fun getFilter(
            filterValue: CharSequence,
            lines: ArrayList<PositionLine>
        ): ArrayList<PositionLine> {
            var filter = filterValue
            return if (filter.isEmpty()) lines
            else {
                val filteredItems = arrayListOf<PositionLine>()
                for (i in lines.indices) {
                    val filterNormalize = Normalizer.normalize(
                        filter.toString().lowercase(Locale.ROOT),
                        Normalizer.Form.NFD
                    )
                        .replace("\\p{InCombiningDiacriticalMarks}+", "")
                    filter = filterNormalize
                    val data = lines[i]
                    val signNormalize =
                        Normalizer.normalize(data.sign.lowercase(Locale.ROOT), Normalizer.Form.NFD)
                            .replace("\\p{InCombiningDiacriticalMarks}+", "")
                    if (signNormalize.contains(filter)) filteredItems.add(data)
                }
                filteredItems
            }
        }
    }
}