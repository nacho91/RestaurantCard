package ar.com.nacho91.restaurantcard.data.api

import ar.com.nacho91.restaurantcard.utils.ResourcesHelper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class RestaurantCardApiManagerTest {

    private val mockWebServer = MockWebServer()

    private lateinit var restaurantCardApiManager: RestaurantCardApiManager

    @Before
    fun setup() {
        mockWebServer.start()

        val url = mockWebServer.url("/").toString()

        val apiClient = RetrofitApiClient(url, 60L, false)

        restaurantCardApiManager = RestaurantCardApiManager(apiClient, "test-key")
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `RestaurantCardApi with key, method and id_restaurant params getMethod() return method response data`() {

        //arrange
        val response = MockResponse()
            .setBody(ResourcesHelper.getJson("json/restaurant_info_response.json"))
            .setResponseCode(200)

        mockWebServer.enqueue(response)

        //act
        val methodResponse = restaurantCardApiManager.getRestaurantInfo("test-id").blockingGet()

        //assert
        val request = mockWebServer.takeRequest()

        assertEquals("GET", request.method)
        assertEquals("/?key=test-key&method=restaurant_get_info&id_restaurant=test-id", request.path)

        assertNotNull(methodResponse.data)
    }

}