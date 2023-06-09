package com.berger.baseapp.features.screen.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.berger.baseapp.features.screen.main.MainActivity
import com.berger.baseapp.utils.Utility.launchActivity
import kotlinx.coroutines.launch

/**
 * Created by merttoptas on 3.05.2022
 */

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        /*lifecycleScope.launchWhenCreated {
            viewModel.uiEvent.collect {
                when (it) {
                    is SplashViewEvent.DirectToDashBoard -> {
                        startMainActivity()
                        finish()
                    }
                }
            }
        }*/

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.uiEvent.collect {
                        when (it) {
                            is SplashViewEvent.DirectToDashBoard -> {
                                startMainActivity()
                                finish()
                            }
                        }
                    }
            }
        }
    }


    private fun startMainActivity() {
        launchActivity(
            packageName = packageName,
            className = MainActivity::class.java.name
        )
    }
}