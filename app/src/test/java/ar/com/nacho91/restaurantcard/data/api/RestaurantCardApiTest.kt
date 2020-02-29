package ar.com.nacho91.restaurantcard.data.api

import ar.com.nacho91.restaurantcard.utils.ResourcesHelper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class RestaurantCardApiTest {

    private val mockWebServer = MockWebServer()

    private lateinit var restaurantCardApi: RestaurantCardApi

    @Before
    fun setup() {
        mockWebServer.start()

        val url = mockWebServer.url("/").toString()

        restaurantCardApi = RetrofitApiClient(url, 60L, false)
            .createService(RestaurantCardApi::class.java)
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

        val queries = mutableMapOf(
            Pair("key", "test-key"),
            Pair("method", "test-method"),
            Pair("id_restaurant", "test-id")
        )

        //act
        val methodResponse = restaurantCardApi.getMethod(queries).blockingGet()

        //assert
        val request = mockWebServer.takeRequest()

        assertEquals("GET", request.method)
        assertEquals("/?key=test-key&method=test-method&id_restaurant=test-id", request.path)

        assertNotNull(methodResponse.data)
    }

}