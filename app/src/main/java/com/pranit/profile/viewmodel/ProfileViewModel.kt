package com.pranit.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pranit.profile.model.ProfileModel
import com.pranit.profile.utils.NavigateEnum
import com.pranit.profile.utils.getAge
import java.util.*

class ProfileViewModel : ViewModel() {

    private val mutableNavigateData = MutableLiveData<NavigateEnum>()
    val selectedNavigateEnum : LiveData<NavigateEnum> get() = mutableNavigateData

    private val mutableProfileData = MutableLiveData<ProfileModel>()
    val updatedProfileData : LiveData<ProfileModel> get() = mutableProfileData


    fun navigateFragment(navigateEnum: NavigateEnum) {
        mutableNavigateData.value = navigateEnum
    }

    fun updateProfileData(profileModel: ProfileModel) {
        mutableProfileData.value = profileModel
    }

    fun calculateAge(dobTimestamp : Long): Triple<Int, Int, Int> {
        val dobDate = Date(dobTimestamp)
        val calendar = Calendar.getInstance()
        calendar.time = dobDate
        return getAge(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH).plus(1), calendar.get(Calendar.DAY_OF_MONTH))
    }
}