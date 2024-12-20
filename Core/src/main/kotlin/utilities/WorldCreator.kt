package core.utilities

import core.utilities.data.*
import core.utilities.enums.*

/**
 * A Kotlin class for generating a json file for [Vehicles].  These files are important for reproducing our results as
 * well as testing various hyperparamters for various vehicle types.
 *
 * @param pixelsPerMeter A scaling factor for drawing
 * @param vehicleType The type of vehicles to generate
 * @param vehicles Configuration data for generating vehicles
 * @param lights Configuration data for generating lights
 * @param obstacles Configuration data for generating obstacles
 * @param food Configuration data for generating food
 * @param homes Configuration data for generating homes
 */
class WorldCreator(
    private val pixelsPerMeter: ScaleConfiguration = ScaleConfiguration.DEFAULT,
    private val vehicleType: VehicleType = VehicleType.BRAITENBERG,
    private val vehicles: VehicleConfiguration = VehicleConfiguration.DEFAULT,
    private val lights: LightConfiguration = LightConfiguration.DEFAULT,
    private val obstacles: ObstacleConfiguration = ObstacleConfiguration.DEFAULT,
    private val food: FoodConfiguration = FoodConfiguration.DEFAULT,
    private val homes: HomeConfiguration = HomeConfiguration.DEFAULT
){
    /**
     * A function for generating world data given configuration parameters
     */
    fun generateWorld() : WorldData {
        val vehicleData = generateVehicles()
        val lightData = generateLights()
        val obstacleData = generateObstacles()
        val foodData = generateFood()
        val homeData = generateHomes()

        return WorldData(pixelsPerMeter.pixelsPerMeter, vehicleType.type, vehicleData, lightData, obstacleData, foodData, homeData)
    }

    /**
     * A function for generating vehicle data
     */
    private fun generateVehicles(): List<VehicleData> {
        val vehicleList = mutableListOf<VehicleData>()
        for (vehicleInstance in 0 until vehicles.count) {
            vehicleList.add(VehicleData(name = vehicles.names[vehicleInstance]))
        }
        return vehicleList
    }

    /**
     * A function for generating light data
     */
    private fun generateLights(): List<LightData> {
        val lightList = mutableListOf<LightData>()
        for (lightInstance in 0 until lights.count) {
            lightList.add(LightData())
        }
        return lightList
    }

    /**
     * A function for generating obstacle data
     */
    private fun generateObstacles(): List<ObstacleData> {
        val obstacleList = mutableListOf<ObstacleData>()
        for (obstacleInstance in 0 until obstacles.count) {
            obstacleList.add(ObstacleData(obstacles.size))
        }
        return obstacleList
    }

    /**
     * A function for generating food data
     */
    private fun generateFood(): List<FoodData> {
        val foodList = mutableListOf<FoodData>()
        val locations = mutableListOf<LocationData>()
        for (foodInstance in 0 until food.count) {
            val location = LocationData(food.distribution)
            locations.add(location)
        }
        foodList.add(FoodData(food.timer, locations))
        return foodList
    }

    /**
     * A function for generating home data
     */
    private fun generateHomes(): List<HomeData> {
        val homeList = mutableListOf<HomeData>()
        for (homeInstance in 0 until homes.count) {
            homeList.add(HomeData(homes.names[homeInstance]))
        }
        return homeList
    }
}