package ar.com.nacho91.restaurantcard.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ar.com.nacho91.restaurantcard.BuildConfig
import ar.com.nacho91.restaurantcard.data.api.RestaurantCardApiManager
import ar.com.nacho91.restaurantcard.data.api.RetrofitApiClient
import ar.com.nacho91.restaurantcard.data.repository.RestaurantCardRepository
import ar.com.nacho91.restaurantcard.domain.entities.Restaurant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RestaurantDetailViewModel : ViewModel() {

    val infoResponse = MutableLiveData<InfoResponse>()

    private var disposable: Disposable? = null

    private val repository: RestaurantCardRepository = RestaurantCardRepository(
        RestaurantCardApiManager(
            RetrofitApiClient(
                BuildConfig.BASE_URL,
                60L,
                BuildConfig.DEBUG
            ),
            BuildConfig.API_KEY
        )
    )

    fun loadRestaurantInfo(restaurantId: String) {
        disposable = repository.getRestaurantInfo(restaurantId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccess, ::onError)
    }

    private fun onSuccess(restaurant: Restaurant) {
        infoResponse.value = InfoResponse.Success(restaurant)
    }

    private fun onError(exception: Throwable) {
        infoResponse.value = InfoResponse.Error
    }

    override fun onCleared() {
        disposable?.dispose()
    }

    sealed class InfoResponse {
        class Success(val restaurant: Restaurant) : InfoResponse()
        object Error : InfoResponse()
    }
}