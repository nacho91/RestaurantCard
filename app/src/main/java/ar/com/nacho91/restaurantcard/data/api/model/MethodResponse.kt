package ar.com.nacho91.restaurantcard.data.api.model

import com.google.gson.annotations.SerializedName

data class MethodResponse(@SerializedName("data") val data: DataResponse)

data class DataResponse(@SerializedName("id_restaurant") val idRestaurant: String,
                        @SerializedName("name") val name: String)