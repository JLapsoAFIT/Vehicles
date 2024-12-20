package core.utilities.enums


/**
 * An enum class for food object configuration values
 *
 * @author Joshua A. Lapso
 * @param timer
 * @param count The number of food objects to create
 * @param distribution The required size of food spawn point +/- around position
 */
enum class FoodConfiguration(
    val timer: Int,
    val count: Int,
    val distribution: List<Int>
) {
    DEFAULT(200, 5, listOf(3,3))
}