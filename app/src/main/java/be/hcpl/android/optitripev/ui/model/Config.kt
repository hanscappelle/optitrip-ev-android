package be.hcpl.android.optitripev.ui.model

data class Config(
    val unit: ConfigUnit = ConfigUnit.Metric,
    val values: List<ConfigValue>,
)

enum class ConfigUnit {
    Metric,
    Imperial,
}

data class ConfigValue(
    val atSpeed: Int,
    val consumption: Float,
)

val defaultConfigValues = mapOf(
    30 to 0.027,
    35 to 0.029,
    40 to 0.032,
    45 to 0.034,
    50 to 0.037,
    55 to 0.041,
    60 to 0.045,
    65 to 0.049,
    70 to 0.053,
    75 to 0.058,
    80 to 0.063,
    85 to 0.068,
    90 to 0.075,
    95 to 0.081,
    100 to 0.089,
    105 to 0.097,
    110 to 0.105,
    115 to 0.115,
    120 to 0.125,
    125 to 0.136,
    130 to 0.148,
    135 to 0.162,
    140 to 0.176,
)