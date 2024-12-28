package utilities.enums

/**
 * An enum class for Vehicle Type strings
 *
 * @author Joshua A. Lapso
 * @param type the string value for each Vehicle Type
 *
 */
enum class VehicleType(val type: String, className: String) {
    BRAITENBERG("Braitenberg", "braitenberg.Braitenberg"),
    BOID("Boid", "boid.Boid"),
    ANT("Ant", "ant.Ant"),
    MIXED("Mixed", "mixed.Mixed")
}