package com.magma.app.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.magma.app.databinding.ActivitySimpleListBinding

class InternshipActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySimpleListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.title.text = "Virtual Internships"
    }
}
