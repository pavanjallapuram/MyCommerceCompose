package com.example.mycommercejetpack.application

import android.app.Application
import com.example.mycommercejetpack.singleton.UserSingleTon
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltAndroidApp
class MyApp: Application() {

    @Inject
    lateinit var userSingleTon: UserSingleTon



    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        CoroutineScope(Dispatchers.IO).launch {
            userSingleTon.initialize()
        }
    }

}