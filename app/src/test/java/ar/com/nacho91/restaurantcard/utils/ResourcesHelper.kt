package ar.com.nacho91.restaurantcard.utils

import java.io.File

object ResourcesHelper {

    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */
    fun getJson(path : String) : String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)

        uri ?: return ""

        val file = File(uri.path)
        return String(file.readBytes())
    }
}