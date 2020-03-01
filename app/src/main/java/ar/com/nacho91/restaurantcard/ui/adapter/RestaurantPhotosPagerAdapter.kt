package ar.com.nacho91.restaurantcard.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import ar.com.nacho91.restaurantcard.R
import com.bumptech.glide.Glide

class RestaurantPhotosPagerAdapter(private val photos: List<String>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.adapter_restaurant_detail_photo, container, false)

        Glide
            .with(view)
            .load(photos[position])
            .centerCrop()
            .into(view as ImageView)

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return 4
    }

}