package be.hcpl.android.optitripev.model

enum class ConfigUnit(val distance: String, val speed: String) {
    Metric("km", "km/h"),
    Imperial("mi", "mph"),
}