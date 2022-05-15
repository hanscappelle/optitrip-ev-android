package be.hcpl.android.optitripev.util

fun Double.toImperial() = (this * Constants.KM_TO_MI)

fun Double.toMetric() = this * Constants.MI_TO_KM

fun Double.formatInt() = this.toInt().toString()

fun Double.formatDouble1() = String.format("%.1f", this)

fun Double.formatDouble3() = String.format("%.3f", this)