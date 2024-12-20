package core.utilities.enums

/**
 * An enum class for obstacle object configuration values
 *
 * @author Joshua A. Lapso
 * @param count The number of obstacle objects to create
 * @param size The size of the obstacle in meters
 */
enum class ObstacleConfiguration(val count: Int, val size: List<Int>) {
    DEFAULT(0, listOf(0,0)),
    FIVE(5, listOf(2,4)),
    TEN(10, listOf(2,4)),
    TWENTYFIVE(25, listOf(2,4)),
    FIFTY(50, listOf(2,4)),
    ONEHUNDRED(100, listOf(2,4))
}