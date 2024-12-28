package utilities.enums


/**
 * An enum class for food object configuration values
 *
 * @author Joshua A. Lapso
 * @param timer
 * @param count The number of food objects to create
 * @param distribution The required size of food spawn point +/- around position
 */
enum class FoodConfiguration(
    val count: Int,
    val timer: Int = 200,
    val distribution: List<Int> = listOf()
) {
    DEFAULT(0, 0, listOf()),
    RELNAV_TEST(2, 200, listOf(0,1)) //distribution is +/- 0 for the first and +/-1 for the second
}