package utilities.enums

/**
 * An enum class for vehicle object configuration values
 *
 * @author Joshua A. Lapso
 * @param count The number of obstacle objects to create
 * @param names The names of the vehicle objects (indexed in order)
 * @param logging A boolean for logging functionality (true=On, false=Off)
 */
enum class VehicleConfiguration(
    val count: Int,
    val names: List<String>,
    val orientation: Int = 0,
    val drawScanLines: Boolean = false,
    val logging: Boolean = false
) {
    DEFAULT(2, listOf("ant.Ant", "ant.Ant"), 0, false, false),
    RELNAV_TEST(2, listOf("RelNavAgent", "RelNavAgent")),
    BOIDS(10, listOf("boid.Boid"), 0, true),
}