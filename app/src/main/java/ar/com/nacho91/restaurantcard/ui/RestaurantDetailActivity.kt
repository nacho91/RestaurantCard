package ar.com.nacho91.restaurantcard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ar.com.nacho91.restaurantcard.R
class RestaurantDetailActivity : AppCompatActivity(R.layout.activity_restaurant_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val model = ViewModelProvider(this).get(RestaurantDetailViewModel::class.java)

        model.infoResponse.observe(this, Observer {  })

        model.loadRestaurantInfo("6861")
    }
}