package com.pranit.profile.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.pranit.profile.R
import com.pranit.profile.model.ProfileModel
import com.pranit.profile.utils.NavigateEnum
import com.pranit.profile.viewmodel.ProfileViewModel

class MainActivity : AppCompatActivity() {

    private val profileViewModel : ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction().add(R.id.container, FirstFragment.newInstance())
                .commit()
        }

        observeNavigationEvent()

    }

    @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
    private fun observeNavigationEvent() {
        profileViewModel.selectedNavigateEnum.observe(this, {
            when (it) {
                NavigateEnum.NAVIGATE_TO_FIRST -> {
                    supportFragmentManager
                        .beginTransaction().replace(R.id.container, FirstFragment.newInstance())
                        .commit()
                }
                NavigateEnum.NAVIGATE_TO_SECOND -> {
                    supportFragmentManager
                        .beginTransaction().replace(R.id.container, SecondFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
        )
    }
}