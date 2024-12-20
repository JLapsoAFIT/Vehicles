package core.utilities.data

import kotlinx.serialization.Serializable
import core.utilities.enums.Locations
import kotlin.random.Random

/**
 * A serializable data class for Obstacles utilized in [Vehicles]
 * @author Joshua A. Lapso
 *
 * @param size Width & Height (required)
 * @param bound_key Optional, default to unbound
 * @param position Obstacle's center (required)
 */
@Serializable
data class ObstacleData(
    val size: List<Int>,
    val bound_key: Int? = null,
    override val position: List<Int> = listOf(
        Random.nextInt(Locations.DEFAULT.range[0], Locations.DEFAULT.range[1]),
        Random.nextInt(Locations.DEFAULT.range[0], Locations.DEFAULT.range[1])
    )
) : AbstractData()