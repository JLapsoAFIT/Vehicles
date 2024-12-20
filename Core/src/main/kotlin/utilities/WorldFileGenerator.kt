package core.utilities

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object WorldFileGenerator {
    /**
     * Entry point for world generator
     * @param args World file arguments
     * @sample
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val worldCreator = WorldCreator()
        val worldData = worldCreator.generateWorld()
        val json = Json.encodeToString(worldData)
        println(json)
    }
}