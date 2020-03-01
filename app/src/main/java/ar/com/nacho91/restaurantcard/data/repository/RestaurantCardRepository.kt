package ar.com.nacho91.restaurantcard.data.repository

import ar.com.nacho91.restaurantcard.data.api.RestaurantCardApiManager
import ar.com.nacho91.restaurantcard.domain.entities.Restaurant
import io.reactivex.Single

class RestaurantCardRepository(private val apiManager: RestaurantCardApiManager) {

    fun getRestaurantInfo(restaurantId: String): Single<Restaurant> {
        return apiManager.getRestaurantInfo(restaurantId).map { response ->
            val data = response.data
            return@map Restaurant(data.idRestaurant, data.name)
        }
    }
}