package ar.com.nacho91.restaurantcard.data.repository

import ar.com.nacho91.restaurantcard.BuildConfig
import ar.com.nacho91.restaurantcard.data.api.RestaurantCardApi
import ar.com.nacho91.restaurantcard.data.api.model.MethodResponse
import ar.com.nacho91.restaurantcard.domain.entities.Restaurant
import io.reactivex.Single

class RestaurantCardRepository(private val restaurantCardApi: RestaurantCardApi) {

    companion object {
        private const val KEY_QUERY = "key"
        private const val METHOD_QUERY = "method"
        private const val ID_RESTAURANT_QUERY = "id_restaurant"
    }

    fun getRestaurantInfo(restaurantId: String): Single<Restaurant> {

        val queries = mutableMapOf(
            Pair(KEY_QUERY, BuildConfig.API_KEY),
            Pair(METHOD_QUERY, "restaurant_get_info"),
            Pair(ID_RESTAURANT_QUERY, restaurantId)
        )

        return restaurantCardApi.getMethod(queries).map { response ->

            val data = response.data

            return@map Restaurant(data.idRestaurant, data.name)
        }
    }

}