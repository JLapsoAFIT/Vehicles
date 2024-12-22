package utilities.data

import utilities.enums.*

/**
 * Configuration data for the [utilities.WorldFileGenerator]
 */
data class GeneratorConfiguration(
    val pixelsPerMeter: Int = ScaleConfiguration.DEFAULT.pixelsPerMeter,
    val vehicleType: VehicleType = VehicleType.ANT,
    val vehicleConfiguration: VehicleConfiguration = VehicleConfiguration.DEFAULT,
    val lightConfiguration: LightConfiguration = LightConfiguration.DEFAULT,
    val obstacleConfiguration: ObstacleConfiguration = ObstacleConfiguration.DEFAULT,
    val foodConfiguration: FoodConfiguration = FoodConfiguration.DEFAULT,
    val homeConfiguration: HomeConfiguration = HomeConfiguration.DEFAULT
)
