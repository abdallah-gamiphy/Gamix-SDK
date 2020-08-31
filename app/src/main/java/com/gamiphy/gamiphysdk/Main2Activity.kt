package com.gamiphy.gamiphysdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.gamiphy.library.GamiBot

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val btn: Button = findViewById(R.id.button)

        btn.setOnClickListener {
            GamiBot.getInstance().logout(this)
        }
    }
}
