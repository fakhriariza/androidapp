package com.lpikitb.lpikitbmobile.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lpikitb.lpikitbmobile.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)


        var handler = android.os.Handler()
        handler.postDelayed({
            var intent = Intent(this@SplashScreenActivity,  MainActivity2::class.java)
            startActivity (intent)
            finish()

        }, 5000)

    }

}