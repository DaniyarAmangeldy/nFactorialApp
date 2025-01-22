package kz.nfactorial.nfactorialapp.home.presentation.service

import android.os.Binder
import android.util.Log

class HomeServiceBinder: Binder() {

    fun printHelloWorld() {
        Log.d("HomeServiceBinder", "Hello World!")
    }
}