package ar.com.nacho91.restaurantcard.repository

import ar.com.nacho91.restaurantcard.data.api.RestaurantCardApi
import ar.com.nacho91.restaurantcard.data.api.model.DataResponse
import ar.com.nacho91.restaurantcard.data.api.model.MethodResponse
import ar.com.nacho91.restaurantcard.data.repository.RestaurantCardRepository
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyMap
import org.mockito.MockitoAnnotations

class RestaurantCardRepositoryTest {

    @Mock
    private lateinit var restaurantCardApi: RestaurantCardApi

    private lateinit var restaurantCardRepository: RestaurantCardRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        restaurantCardRepository = RestaurantCardRepository(restaurantCardApi)
    }

    @Test
    fun `RestaurantCardRepository with method response getRestaurantInfo() returns valid restaurant`() {

        //assert
        `when`(restaurantCardApi.getMethod(anyMap())).thenReturn(Single.just(MethodResponse(
            DataResponse("1234", "Name")
        )))

        //act
        val restaurant = restaurantCardRepository.getRestaurantInfo("1234").blockingGet()

        //arrange
        assertEquals("1234", restaurant.id)
        assertEquals("Name", restaurant.name)
    }
}