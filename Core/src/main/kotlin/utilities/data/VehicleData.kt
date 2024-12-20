package core.utilities.data

import core.utilities.enums.Locations
import kotlinx.serialization.Serializable
import kotlin.random.Random

/**
 * A serializable data class for vehicle objects utilized in [Vehicles]
 * @author Joshua A. Lapso
 *
 * @param name To load a class need full package name "Sample.Callie", or filename "Marie", to load JSON file in data folder
 * @param position optional, default to random
 * @param draw_scan_lines optional, default to false
 * @param network optional, default to false
 * @param home optional, default to N/A
 */
@Serializable
data class VehicleData(
    val name: String,
    val draw_scan_lines: Boolean = false,
    val home: List<Int>? = null,
    val network: Boolean = false,
    override val position: List<Int> = listOf(
        Random.nextInt(Locations.DEFAULT.range[0], Locations.DEFAULT.range[1]),
        Random.nextInt(Locations.DEFAULT.range[0], Locations.DEFAULT.range[1])
    )
): AbstractData()
