package utilities

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import utilities.data.GeneratorConfiguration
import utilities.enums.*
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Instant

object WorldFileGenerator {
    /**
     * Entry point for world generator
     * @param args World file arguments
     *
     * **World**
     * args[0]: Pixels per meter (Integer)
     * args[1]: Vehicle type (String)--ANT|BOID|BRAITENBERG|MIXED--
     * args[2]: Name of [utilities.enums.VehicleConfiguration] instance
     * args[3]: Name of [utilities.enums.LightConfiguration] instance
     * args[4]: Name of [utilities.enums.ObstacleConfiguration] instance
     * args[5]: Name of [utilities.enums.FoodConfiguration] instance
     * args[6]: Name of [utilities.enums.HomeConfiguration] instance
     *
     * **Usage**
     * java -jar worldGenerator.jar <pixelsPerMeter> <vehicleType> <vehicleConfig> <lightConfig> <obstacleConfig> <foodConfig> <homeConfig>
     *
     * EXAMPLES:
     * 1) All configurations specified and strings for getting enum classes
     * java -jar worldGenerator.jar 12 Ant RELNAV_TEST DEFAULT DEFAULT RELNAV_TEST ONE
     *
     * 2) Nothing sepicified and all defaults used.
     * java -jar worldGenerator.jar
     */
    @JvmStatic
    fun main(args: Array<String>) {

        val worldCreator = WorldCreator()

        val argData = when (args.size) {
            7 -> parseCommandLine(args) // command line values
            else -> useDefaults() // TODO: Currently all or none...needs to be more modular.
        }

        // Create the world instance with defaults
        val worldData = worldCreator.generateWorld(argData)

        // Encode to JSON
        val jsonWorld = Json.encodeToString(worldData)

        // Write to file.
        val timeStamp = Instant.now().toEpochMilli()
        val fileName = "./data/generatorOutput/worldFile_${timeStamp}.json"
        Files.write(Paths.get(fileName), jsonWorld.toByteArray())
    }

    private fun parseCommandLine(arguments: Array<String>): GeneratorConfiguration {
        return GeneratorConfiguration(
            arguments[0].toInt(),
            VehicleType.valueOf(arguments[1].uppercase()),
            VehicleConfiguration.valueOf(arguments[2]),
            LightConfiguration.valueOf(arguments[3]),
            ObstacleConfiguration.valueOf(arguments[4]),
            FoodConfiguration.valueOf(arguments[5]),
            HomeConfiguration.valueOf(arguments[6])
        )
    }

    private fun useDefaults(): GeneratorConfiguration {
        return GeneratorConfiguration()
    }
}