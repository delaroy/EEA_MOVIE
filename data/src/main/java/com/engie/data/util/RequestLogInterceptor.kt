package com.engie.data.util


import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import timber.log.Timber
import java.io.IOException

class RequestLogInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response = chain.proceed(request)
        val rawJson = response.body!!.string()

        try {
            val `object` = JSONTokener(rawJson).nextValue()
            val jsonLog = if (`object` is JSONObject)
                `object`.toString(4)
            else
                (`object` as JSONArray).toString(4)
            Timber.d("${request.url}: $jsonLog")
        } catch (e: Exception) {
            Timber.e("URL: ${request.url} \n Response: $rawJson")
            e.printStackTrace()
        }

        // Re-create the response before returning it because body can be read only once
        return response.newBuilder()
            .body(rawJson.toResponseBody(response.body!!.contentType())).build()
    }
}