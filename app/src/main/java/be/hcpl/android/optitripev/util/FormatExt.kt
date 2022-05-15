package be.hcpl.android.optitripev.util

fun Double.toImperial() = (this * Constants.KPH_TO_MPH)

fun Double.toMetric() = this * Constants.MPH_TO_KPH

fun Double.formatInt() = this.toInt().toString()

fun Double.formatDouble1() = String.format("%.1f", this)

fun Double.formatDouble3() = String.format("%.3f", this)