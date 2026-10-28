package com.karimali.movieverse.core.utils

sealed class AppException(message: String) : Exception(message) {
    class NoInternet : AppException("لا يوجد اتصال بالإنترنت")
    class Server(val code: Int) : AppException("خطأ في السيرفر ($code)")
    class Unknown(message: String?) : AppException(message ?: "حدث خطأ غير متوقع")
}