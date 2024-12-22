package utilities.data

import kotlinx.serialization.Serializable

/**
 * A base class for polymorphic serialization
 * @author Joshua A. Lapso
 * @property position A positional element for data objects
 */
@Serializable
sealed class AbstractData() {
    abstract val position: List<Int>
}
