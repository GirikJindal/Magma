package com.magma.app

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.magma.app.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: ui.MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Mobile Ads and load banner
        try {
            MobileAds.initialize(this) {}
            val adRequest = AdRequest.Builder().build()
            binding.adView.loadAd(adRequest)
        } catch (e: Exception) {
            // ignore for dev without Google Play services
        }

        // Redirect to Login if user not signed in
        val authRepo = com.magma.app.data.AuthRepository()
        if (authRepo.currentUser() == null) {
            startActivity(android.content.Intent(this, com.magma.app.auth.LoginActivity::class.java))
            finish()
            return
        }

        vm.colleges.observe(this, Observer { colleges ->
            binding.tvSummary.text = "Colleges: ${colleges.size} • Points: ${vm.totalPoints()}"
        })

        binding.btnAddDemo.setOnClickListener {
            vm.addDemoData()
        }

        binding.btnForums.setOnClickListener { startActivity(android.content.Intent(this, com.magma.app.features.ForumsActivity::class.java)) }
        binding.btnStore.setOnClickListener { startActivity(android.content.Intent(this, com.magma.app.features.StoreActivity::class.java)) }
        binding.btnAppStore.setOnClickListener { startActivity(android.content.Intent(this, com.magma.app.features.AppStoreActivity::class.java)) }
        binding.btnVideos.setOnClickListener { startActivity(android.content.Intent(this, com.magma.app.features.VideosActivity::class.java)) }
        binding.btnJobs.setOnClickListener { startActivity(android.content.Intent(this, com.magma.app.features.JobsActivity::class.java)) }
        binding.btnEvents.setOnClickListener { startActivity(android.content.Intent(this, com.magma.app.features.EventsActivity::class.java)) }
        binding.btnLibrary.setOnClickListener { startActivity(android.content.Intent(this, com.magma.app.features.LibraryActivity::class.java)) }
        binding.btnMentorship.setOnClickListener { startActivity(android.content.Intent(this, com.magma.app.features.MentorshipActivity::class.java)) }
        binding.btnInternships.setOnClickListener { startActivity(android.content.Intent(this, com.magma.app.features.InternshipActivity::class.java)) }
        binding.btnProfile.setOnClickListener { startActivity(android.content.Intent(this, com.magma.app.features.ProfileActivity::class.java)) }
    }
}
