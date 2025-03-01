package be.hcpl.android.optitripev.ui.components

import be.hcpl.android.optitripev.domain.Constants.Companion.KM_TO_MI
import be.hcpl.android.optitripev.domain.Constants.Companion.MI_TO_KM
import be.hcpl.android.optitripev.domain.Constants.Companion.WH_KM_TO_WH_MI
import be.hcpl.android.optitripev.model.ConfigUnit
import be.hcpl.android.optitripev.model.ConfigUnit.Imperial
import kotlin.math.round

fun Int.formattedSpeedValue(u: ConfigUnit) = if (u == Imperial) round(this * KM_TO_MI).toInt() else this

fun Float.convertedValue(u: ConfigUnit) = if (u == Imperial) this * WH_KM_TO_WH_MI else this

fun Float.formattedValue(u: ConfigUnit) = round(convertedValue(u) * 1000) / 1000

fun Float.readableValue(u: ConfigUnit) = (this.convertedValue(u) * 1000).toInt()

fun Float.commonValue(u: ConfigUnit) = round(this.convertedValue(u) * 10_000) / 100

fun String.readableToStored() = (this.toDoubleOrNull()?.let { it / 1000 } ?: 0).toString()

fun Double.toImperial() = this * KM_TO_MI

fun Double.toMetric() = this * MI_TO_KM

fun formatHours(hoursDecimal: Float): String {
    val hours = hoursDecimal.toInt()
    val minutes = ((hoursDecimal - hours) * 60).toInt()
    return "${hours}h ${minutes}m"
    //val date: Date = SimpleDateFormat("HH:mm").parse(TimeString)
    //val newTimeString: String = SimpleDateFormat("H:mm").format(date)
}