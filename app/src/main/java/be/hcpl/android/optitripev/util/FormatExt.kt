package be.hcpl.android.optitripev.util

fun Double.toImperial() = (this * Constants.KM_TO_MI)

fun Double.toMetric() = this * Constants.MI_TO_KM

fun Double.formatInt() = this.toInt().toString()

fun Double.formatDouble1() = String.format("%.1f", this)

fun Double.formatDouble3() = String.format("%.3f", this)

fun formatHours(hoursDecimal: Float) : String {
    val hours = hoursDecimal.toInt()
    val minutes = ((hoursDecimal - hours) * 60).toInt()
    return "${hours}h ${minutes}m"
    //val date: Date = SimpleDateFormat("HH:mm").parse(TimeString)
    //val newTimeString: String = SimpleDateFormat("H:mm").format(date)
}