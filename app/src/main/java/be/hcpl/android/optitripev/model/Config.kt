package be.hcpl.android.optitripev.model

data class Config(
    val unit: ConfigUnit = ConfigUnit.Metric,
    val values: List<ConfigValue>,
)