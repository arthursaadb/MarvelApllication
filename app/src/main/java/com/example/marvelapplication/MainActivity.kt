package com.example.marvelapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelapplication.presentation.CharacterFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, CharacterFragment.newInstance()).commit()
    }

}