package id.herald.core.network.interceptor

import id.herald.core.util.UtilFunctions.logE
import okhttp3.Interceptor
import okhttp3.Response

/** Created by Herald Santos on 04/12/2024. */

internal class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(originalRequest.url).build()
        logE("HttpRequestInterceptor : $request")
        return chain.proceed(request)
    }
}