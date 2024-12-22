package utilities.enums

/**
 * An enum class for Ant Type strings
 *
 * @author Joshua A. Lapso
 * @param possibleTypes the list of string values for each Ant Type
 *
 */

enum class AntTypes(val possibleTypes: List<String>) {
    DEFAULT(listOf("A", "B", "C", "D", "E")),
    EXTENDED(listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")),
    FULL(
        listOf(
            "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
        )
    )
}