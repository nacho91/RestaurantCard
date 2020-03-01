package ar.com.nacho91.restaurantcard.repository

import ar.com.nacho91.restaurantcard.data.api.RestaurantCardApiManager
import ar.com.nacho91.restaurantcard.data.api.model.DataResponse
import ar.com.nacho91.restaurantcard.data.api.model.MethodResponse
import ar.com.nacho91.restaurantcard.data.repository.RestaurantCardRepository
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RestaurantCardRepositoryTest {

    @Mock
    private lateinit var restaurantCardApiManager: RestaurantCardApiManager

    private lateinit var restaurantCardRepository: RestaurantCardRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        restaurantCardRepository = RestaurantCardRepository(restaurantCardApiManager)
    }

    @Test
    fun `RestaurantCardRepository with method response getRestaurantInfo() returns valid restaurant`() {

        //assert
        `when`(restaurantCardApiManager.getRestaurantInfo("1234")).thenReturn(Single.just(MethodResponse(
            DataResponse("1234", "Name")
        )))

        //act
        val restaurant = restaurantCardRepository.getRestaurantInfo("1234").blockingGet()

        //arrange
        assertEquals("1234", restaurant.id)
        assertEquals("Name", restaurant.name)
    }
}