package ar.com.nacho91.restaurantcard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ar.com.nacho91.restaurantcard.BuildConfig
import ar.com.nacho91.restaurantcard.R
import ar.com.nacho91.restaurantcard.data.api.RestaurantCardApiManager
import ar.com.nacho91.restaurantcard.data.api.RetrofitApiClient
import ar.com.nacho91.restaurantcard.data.repository.RestaurantCardRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RestaurantDetailActivity : AppCompatActivity(R.layout.activity_restaurant_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = RestaurantCardRepository(
            RestaurantCardApiManager(
                RetrofitApiClient(
                    BuildConfig.BASE_URL,
                    60L,
                    BuildConfig.DEBUG
                ),
                BuildConfig.API_KEY
            )
        )

        repository.getRestaurantInfo("6861")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}