package com.magma.app

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.android.gms.ads.MobileAds

class MagmaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase if `google-services.json` is provided
        try {
            FirebaseApp.initializeApp(this)
        } catch (e: Exception) {
            // no-op: app may work without Firebase during local dev
        }

        // Initialize Mobile Ads (AdMob)
        try {
            MobileAds.initialize(this) {}
        } catch (e: Exception) {
            // ignore initialization errors in dev
        }
    }
}
