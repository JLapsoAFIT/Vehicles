package core.utilities.enums
/**
 * An enum class for home object configuration values
 *
 * @author Joshua A. Lapso
 * @param count The number of home objects to create
 * @param names The names of the home objects (indexed in order)
 */
enum class HomeConfiguration(val count: Int, val names: List<String>) {
    DEFAULT(0, listOf()),
    TWO(2, listOf("Home1", "Home2")),
}