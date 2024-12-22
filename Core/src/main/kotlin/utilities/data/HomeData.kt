package utilities.data

import kotlinx.serialization.Serializable
import utilities.enums.Locations
import kotlin.random.Random

/**
 * A serializable data class for home objects utilized in [Vehicles]
 * @author Joshua A. Lapso
 *
 * @param name The name of the home object.
 * @param position optional, default to random
 * @param resource optional, default to 0
 *
 */
@Serializable
data class HomeData(
    val name: String,
    val resource: Int = 0,
    override val position: List<Int> = listOf(
        Random.nextInt(Locations.DEFAULT.range[0], Locations.DEFAULT.range[1]),
        Random.nextInt(Locations.DEFAULT.range[0], Locations.DEFAULT.range[1])
    )
): AbstractData()
