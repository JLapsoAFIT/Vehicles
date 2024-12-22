package utilities.data

import kotlinx.serialization.Serializable

/**
 * A serializable data class for food objects utilized in [Ants]
 * @author Joshua A. Lapso
 *
 * @param timer Number of steps between food respawns
 * @param locations Required
 */
@Serializable
data class FoodData(
    val timer: Int,
    val locations: List<LocationData>
)