package core.utilities.enums

/**
 * An enum class for vehicle object configuration values
 *
 * @author Joshua A. Lapso
 * @param count The number of obstacle objects to create
 * @param names The names of the vehicle objects (indexed in order)
 */
enum class VehicleConfiguration(val count: Int, val names: List<String>) {
    DEFAULT(5, listOf("Agent1", "Agent2", "Agent3", "Agent4", "Agent5"))
}