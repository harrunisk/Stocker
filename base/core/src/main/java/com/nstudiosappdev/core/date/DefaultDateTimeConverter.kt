package date

import java.text.SimpleDateFormat
import java.util.*

class DefaultDateTimeConverter : DateTimeConverter {

    override fun convertLongToDate(dateTime: Long, dateFormat: String): String {
        val sdf = SimpleDateFormat(dateFormat)
        val date = Date(dateTime)
        return sdf.format(date)
    }

    override fun convertCurrentDateToLong(): Long = Date().time
}