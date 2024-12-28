package utilities

import utilities.data.*
import utilities.enums.*

/**
 * A Kotlin class for generating a json file for [vehicles.Vehicles].  These files are important for reproducing our results as
 * well as testing various hyperparamters for various vehicle types.
 *
 * @param pixelsPerMeter A scaling factor for drawing
 * @param vehicles Configuration data for generating vehicles
 * @param lights Configuration data for generating lights
 * @param obstacles Configuration data for generating obstacles
 * @param food Configuration data for generating food
 * @param homes Configuration data for generating homes
 */
class WorldCreator(){
    /**
     * A function for generating world data given configuration parameters
     */
    fun generateWorld(worldConfig: GeneratorConfiguration) : WorldData {
        val vehicleData = generateVehicles(worldConfig.vehicleConfiguration, worldConfig.homeConfiguration)
        val lightData = generateLights(worldConfig.lightConfiguration)
        val obstacleData = generateObstacles(worldConfig.obstacleConfiguration)
        val foodData = generateFood(worldConfig.foodConfiguration)
        val homeData = generateHomes(worldConfig.homeConfiguration)

        return WorldData(worldConfig.pixelsPerMeter, worldConfig.vehicleType.type, vehicleData, lightData, obstacleData, foodData, homeData)
    }

    /**
     * A function for generating vehicle data
     */
    private fun generateVehicles(vehicleConfiguration: VehicleConfiguration, homeConfiguration: HomeConfiguration): List<VehicleData> {
        val vehicleList = mutableListOf<VehicleData>()
        //TODO: Currently one repeated class type or several as a list...
        val vehicleNames = when (vehicleConfiguration.names.size) {
            1 -> List(vehicleConfiguration.count) {
                vehicleConfiguration.names.first()
            }
            else -> vehicleConfiguration.names
        }
        //TODO: Currently each vehicle has its own home or they all share a home...
        val vehicleHomes = when (homeConfiguration.names.size == vehicleConfiguration.count) {
            true -> homeConfiguration.names
            false -> List(vehicleConfiguration.count) {
                when(homeConfiguration.names.isEmpty()) {
                    true -> null
                    false -> homeConfiguration.names.first()
                }
            }
        }
        for (vehicleInstance in 0 until vehicleConfiguration.count) {
            vehicleList.add(
                VehicleData(
                    name = vehicleNames[vehicleInstance],
                    logging = vehicleConfiguration.logging,
                    drawScanLines = vehicleConfiguration.drawScanLines,
                    home = vehicleHomes[vehicleInstance],
                    resource = vehicleInstance.toString().padStart(3, '0')
                )
            )
        }
        return vehicleList
    }

    /**
     * A function for generating light data
     */
    private fun generateLights(lightConfiguration: LightConfiguration): List<LightData> {
        val lightList = mutableListOf<LightData>()
        for (lightInstance in 0 until lightConfiguration.count) {
            lightList.add(LightData())
        }
        return lightList
    }

    /**
     * A function for generating obstacle data
     */
    private fun generateObstacles(obstacleConfiguration: ObstacleConfiguration): List<ObstacleData> {
        val obstacleList = mutableListOf<ObstacleData>()
        for (obstacleInstance in 0 until obstacleConfiguration.count) {
            obstacleList.add(ObstacleData(obstacleConfiguration.size))
        }
        return obstacleList
    }

    /**
     * A function for generating food data
     */
    private fun generateFood(foodConfiguration: FoodConfiguration): List<FoodData> {
        val foodList = mutableListOf<FoodData>()
        val distributionList = when(foodConfiguration.distribution.size == foodConfiguration.count){
            true -> List(foodConfiguration.count) {
                listOf(foodConfiguration.distribution[it], foodConfiguration.distribution[it])
            }
            false -> List(foodConfiguration.count) {
                listOf(foodConfiguration.distribution[0], foodConfiguration.distribution[0])
            }
        }
        val locations = mutableListOf<LocationData>()
        for (foodInstance in 0 until foodConfiguration.count) {
            val location = LocationData(distributionList[foodInstance])
            locations.add(location)
        }
        foodList.add(FoodData(foodConfiguration.timer, locations))
        return foodList
    }

    /**
     * A function for generating home data
     */
    private fun generateHomes(homeConfiguration: HomeConfiguration): List<HomeData> {
        val homeList = mutableListOf<HomeData>()
        for (homeInstance in 0 until homeConfiguration.count) {
            homeList.add(HomeData(homeConfiguration.names[homeInstance]))
        }
        return homeList
    }
}