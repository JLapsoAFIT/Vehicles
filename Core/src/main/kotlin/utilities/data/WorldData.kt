package core.utilities.data

import kotlinx.serialization.Serializable
/**
 * A serializable data class for world files
 * @author Joshua A. Lapso
 *
 * @param pixels_per_meter Required Camera scaling critical for position
 * @param vehicle_type Required string from [utilities.enums.VehicleType]
 * @param vehicles Required Array
 * @param lights Optional Array
 * @param obstacles Optional Array
 * @param food Optional Array
 * @param homes Optional Array
 */

@Serializable
data class WorldData(
    val pixelsPerMeter: Int,
    val vehicleType: String,
    val vehicles: List<VehicleData>,
    val lights: List<LightData>,
    val obstacles: List<ObstacleData>,
    val food: List<FoodData>,
    val homes: List<HomeData>
)