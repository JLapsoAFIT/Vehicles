package utilities.enums

/**
 * An enum class for home object configuration values
 *
 * @author Joshua A. Lapso
 * @param count The number of home objects to create
 * @param names The names of the home objects (indexed in order)
 * @param logging A boolean for logging functionality (true=On, false=Off)
 */
enum class HomeConfiguration(
    val count: Int,
    val names: List<String>,
    val resource: Int = 20,
    val logging: Boolean = false
) {
    DEFAULT(0, listOf(), 0, false),
    ONE(1, listOf("CallieHome"), 20),
    TWO(2, listOf("Home1", "Home2")),
}