package ar.com.nacho91.restaurantcard.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import ar.com.nacho91.restaurantcard.R
import ar.com.nacho91.restaurantcard.domain.entities.Restaurant
import ar.com.nacho91.restaurantcard.ui.adapter.RestaurantPhotosPagerAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class RestaurantDetailActivity : AppCompatActivity(R.layout.activity_restaurant_detail) {

    private val name by lazy { findViewById<TextView>(R.id.restaurant_detail_name) }
    private val photosPager by lazy { findViewById<ViewPager>(R.id.restaurant_detail_photos_pager) }
    private val photosIndicator by lazy { findViewById<DotsIndicator>(R.id.restaurant_detail_photos_pager_indicator) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(findViewById(R.id.restaurant_detail_toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = ""

        val model = ViewModelProvider(this).get(RestaurantDetailViewModel::class.java)

        model.infoResponse.observe(this, Observer {
            when (it) {
                is RestaurantDetailViewModel.InfoResponse.Success -> setupRestaurant(it.restaurant)
            }
        })

        model.loadRestaurantInfo("6861")
    }

    private fun setupRestaurant(restaurant: Restaurant) {
        name.text = restaurant.name
        photosPager.adapter = RestaurantPhotosPagerAdapter()
        photosIndicator.setViewPager(photosPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}