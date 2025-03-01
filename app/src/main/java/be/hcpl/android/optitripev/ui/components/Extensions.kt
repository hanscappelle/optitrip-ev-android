package be.hcpl.android.optitripev.ui.components

import be.hcpl.android.optitripev.domain.Constants
import be.hcpl.android.optitripev.domain.Constants.Companion.KM_TO_MI
import be.hcpl.android.optitripev.domain.Constants.Companion.WH_KM_TO_WH_MI
import be.hcpl.android.optitripev.model.ConfigUnit
import kotlin.math.round

// TODO improve upon this

fun Int.formattedSpeedValue(u: ConfigUnit) = if (u == ConfigUnit.Imperial) round(this * KM_TO_MI).toInt() else this

fun Float.convertedValue(u: ConfigUnit) = if (u == ConfigUnit.Imperial) this * WH_KM_TO_WH_MI else this.toDouble()

fun Float.formattedValue(u: ConfigUnit) = round(convertedValue(u) * 1000) / 1000

fun Float.readableValue(u: ConfigUnit) = round(this.convertedValue(u) * 100_000) / 100

fun Float.commonValue(u: ConfigUnit) = round(this.convertedValue(u) * 10_000) / 100

fun String.readableToStored(u: ConfigUnit) = (this.toDouble() / 1000).toString()

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