package core.utilities.enums
/**
 * An enum class for scale object configuration values
 *
 * @author Joshua A. Lapso
 * @param pixelsPerMeter The scale value for drawing functions
 */
enum class ScaleConfiguration(val pixelsPerMeter: Int) {
    DEFAULT(10),
    TWENTY(20),
    THIRTY(30),
    FORTY(40),
    FIFTY(50)
}