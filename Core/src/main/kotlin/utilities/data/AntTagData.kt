package core.utilities.data

import kotlinx.serialization.Serializable

/**
 * A serializable data class for tags utilized in [Ants]
 * @author Joshua A. Lapso
 * @param interActionTag
 * @param matingTag
 * @param offenseTag
 * @param defenseTag
 * @param tradingTag
 * @param combatCondition
 * @param tradeCondition
 * @param matingCondition
 * @param position
 */
@Serializable
data class AntTagData(
    val interActionTag: String,
    val matingTag: String,
    val offenseTag: String,
    val defenseTag: String,
    val tradingTag: String,
    val combatCondition: String,
    val tradeCondition: String,
    val matingCondition: String,
    override val position: List<Int>,
) : AbstractData()