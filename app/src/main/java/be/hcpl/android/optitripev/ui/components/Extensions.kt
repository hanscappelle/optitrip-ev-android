package be.hcpl.android.optitripev.ui.components

import be.hcpl.android.optitripev.model.ConfigUnit
import be.hcpl.android.optitripev.util.Constants.Companion.KM_TO_MI
import be.hcpl.android.optitripev.util.Constants.Companion.WH_KM_TO_WH_MI
import kotlin.math.round

// TODO improve upon this

fun Int.formattedSpeedValue(u: ConfigUnit) = if (u == ConfigUnit.Imperial) round(this * KM_TO_MI).toInt() else this

fun Float.convertedValue(u: ConfigUnit) = if (u == ConfigUnit.Imperial) this * WH_KM_TO_WH_MI else this.toDouble()

fun Float.formattedValue(u: ConfigUnit) = round(convertedValue(u) * 1000) / 1000

fun Float.readableValue(u: ConfigUnit) = round(this.convertedValue(u) * 100_000) / 100

fun Float.commonValue(u: ConfigUnit) = round(this.convertedValue(u) * 10_000) / 100

fun String.readableToStored(u: ConfigUnit) = (this.toDouble() / 1000).toString()
