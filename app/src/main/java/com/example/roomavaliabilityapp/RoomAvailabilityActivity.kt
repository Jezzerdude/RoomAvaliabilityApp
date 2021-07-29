package com.example.roomavaliabilityapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.roomavaliabilityapp.databinding.ActivityAppBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomAvailabilityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
    }
}