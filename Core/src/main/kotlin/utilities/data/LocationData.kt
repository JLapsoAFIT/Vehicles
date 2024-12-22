package utilities.data

import kotlinx.serialization.Serializable
import utilities.enums.Locations
import kotlin.random.Random

/**
 * A serializable data class for location objects utilized in [Ants]
 * @author Joshua A. Lapso
 *
 * @param distribution Required size of food spawn point +/- around position
 * @param position Required Center point of food spawn point
 */
@Serializable
data class LocationData(
    val distribution: List<Int>,
    override val position: List<Int> = listOf(
        Random.nextInt(Locations.DEFAULT.range[0], Locations.DEFAULT.range[1]),
        Random.nextInt(Locations.DEFAULT.range[0], Locations.DEFAULT.range[1])
    )
): AbstractData()