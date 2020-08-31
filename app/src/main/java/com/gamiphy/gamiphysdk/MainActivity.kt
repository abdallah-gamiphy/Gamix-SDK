package com.gamiphy.gamiphysdk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gamiphy.library.GamiBot
import com.gamiphy.library.actions.OnAuthTrigger
import com.gamiphy.library.models.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.bot)
        val button2 = findViewById<Button>(R.id.newone)

        button2.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    Main2Activity::class.java
                )
            )
        }

        GamiBot.getInstance().registerGamiphyOnAuthTrigger(object : OnAuthTrigger {
            override fun onAuthTrigger(signUp: Boolean) {
                // make your action here, you may start login activity
                Log.d("onAuthTrigger", "$signUp")

            }
        })

        findViewById<Button>(R.id.login).setOnClickListener {
            GamiBot.getInstance().login(
                User(
                    firstName = "Riyad",
                    lastName = "Yahya",
                    email = "riyad@gamiphy.co",
                    hash = "93b2844c18a8804dee394ee73efde8294ad263c1488ab8522d93dfd55dcb65fb"
                )
            )
        }

        button.setOnClickListener {
            GamiBot.getInstance().open(this)
        }
    }
}
