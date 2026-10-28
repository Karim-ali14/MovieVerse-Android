package com.karimali.movieverse.core.utils

import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException

suspend inline fun <T> safeApiCall(crossinline block : suspend () -> T) = try {
    block()
}catch (e: CancellationException){
    throw e
} catch (e: IOException) {
    throw AppException.NoInternet()
} catch (e: HttpException) {
    throw AppException.Server(e.code())
} catch (e: Exception) {
    throw AppException.Unknown(e.localizedMessage)
}
