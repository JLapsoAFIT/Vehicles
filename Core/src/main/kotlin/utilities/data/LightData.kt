package core.utilities.data

import kotlinx.serialization.Serializable
import core.utilities.enums.Locations
import kotlin.random.Random

/**
 * A serializable data class for light objects utilized in
 *
 *
 * @author Joshua A. Lapso
 * @param bound_key Optional, default to unbound
 * @param position Location (required)
 */
@Serializable
data class LightData(
    val bound_key: Int? = null,
    override val position: List<Int> = listOf(
        Random.nextInt(Locations.DEFAULT.range[0], Locations.DEFAULT.range[1]),
        Random.nextInt(Locations.DEFAULT.range[0], Locations.DEFAULT.range[1])
    )
) : AbstractData()