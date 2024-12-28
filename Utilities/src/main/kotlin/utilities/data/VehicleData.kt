package utilities.data

import utilities.enums.Locations
import kotlinx.serialization.Serializable
import kotlin.random.Random

/**
 * A serializable data class for vehicle objects utilized in [vehicles.Vehicles]
 * @author Joshua A. Lapso
 *
 * @param name To load a class need full package name "courses.sample.Callie", or filename "Marie", to load JSON file in data folder
 * @param position optional, default to random
 * @param drawScanLines optional, default to false
 * @param orientation ?optional? TODO: look at code and describe this better
 * @param network optional, default to false
 * @param logging optional, default to false
 * @param home optional, default to null
 */
@Serializable
data class VehicleData(
    val name: String,
    val resource: String,
    override val position: List<Int> = listOf(
        Random.nextInt(Locations.DEFAULT.range[0], Locations.DEFAULT.range[1]),
        Random.nextInt(Locations.DEFAULT.range[0], Locations.DEFAULT.range[1])
    ),
    val drawScanLines: Boolean = false,
    val orientation: Int = 0,
    val home: String? = null,
    val network: Boolean = false,
    val logging: Boolean = false,
): AbstractData()
