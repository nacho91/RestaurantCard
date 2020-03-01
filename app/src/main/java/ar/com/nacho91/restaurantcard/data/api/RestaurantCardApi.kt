package ar.com.nacho91.restaurantcard.data.api

import ar.com.nacho91.restaurantcard.data.api.model.MethodResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RestaurantCardApi {

    @GET("api")
    fun getMethod(@QueryMap values: Map<String, String>): Single<MethodResponse>

}