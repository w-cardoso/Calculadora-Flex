package com.example.calculadoraflexwevs

import androidx.appcompat.app.AppCompatActivity
import com.example.calculadoraflexwevs.utils.CalculaFlexTracker
import com.example.calculadoraflexwevs.utils.ScreenMap


open class BaseActivity : AppCompatActivity() {
    open fun getScreenName(): String {
        return ScreenMap.getScreenNameBy(this)
    }

    override fun onStart() {
        super.onStart()
        CalculaFlexTracker.trackScreen(this, getScreenName())
    }
}