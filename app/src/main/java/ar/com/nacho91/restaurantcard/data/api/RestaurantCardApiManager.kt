package ar.com.nacho91.restaurantcard.data.api

import ar.com.nacho91.restaurantcard.BuildConfig
import ar.com.nacho91.restaurantcard.data.api.model.MethodResponse
import io.reactivex.Single

class RestaurantCardApiManager(retrofitApiClient: RetrofitApiClient, private val apiKey: String) {

    companion object {
        private const val KEY_QUERY = "key"
        private const val METHOD_QUERY = "method"
        private const val ID_RESTAURANT_QUERY = "id_restaurant"
    }

    private val api = retrofitApiClient.createService(RestaurantCardApi::class.java)

    fun getRestaurantInfo(restaurantId: String): Single<MethodResponse> {

        val queries = mutableMapOf(
            Pair(KEY_QUERY, apiKey),
            Pair(METHOD_QUERY, "restaurant_get_info"),
            Pair(ID_RESTAURANT_QUERY, restaurantId)
        )

        return api.getMethod(queries)
    }

}