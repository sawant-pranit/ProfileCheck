package com.pranit.profile.view

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.pranit.profile.R
import com.pranit.profile.model.ProfileModel
import com.pranit.profile.utils.NavigateEnum
import com.pranit.profile.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.layout_back_button.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.util.*

class FirstFragment : Fragment(){

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment.
         *
         * @return A new instance of fragment SecondFragment.
         */
        @JvmStatic
        fun newInstance() =
            FirstFragment()
    }

    private val profileViewModel : ProfileViewModel by activityViewModels()

    private val dobPicker = MaterialDatePicker.Builder.datePicker().build()

    private var selectedDob : Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle()
        setClickListeners()
        setDobDatePicker()
    }

    private fun setDobDatePicker() {
        dobPicker.addOnPositiveButtonClickListener {
            selectedDob = it
            btnDOB.text = dobPicker.headerText
            Log.i("DOB", "Selected Date ${Date(it)} ")
        }
    }

    private fun setClickListeners() {

        btnDOB.setOnClickListener {
            dobPicker.show(childFragmentManager, "dob")
        }

        btnSubmit.setOnClickListener {
            if(validateData()) {
                val profileData = ProfileModel(etxtFirstName.text.toString(), etxtSecondName.text.toString(), selectedDob)
                profileViewModel.updateProfileData(profileData)
                profileViewModel.navigateFragment(NavigateEnum.NAVIGATE_TO_SECOND)
            } else {
                Toast.makeText(requireContext(), "Please enter valid data", Toast.LENGTH_SHORT)
                    .show()
            }
        }



        clBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setTitle() {
        txtvTitle.text = getString(R.string.title_first_fragment)
    }

    private fun validateData() : Boolean {
        val firstName = etxtFirstName.text.toString()
        val secondName = etxtSecondName.text.toString()

        when{
            firstName.isNullOrEmpty() -> return false
            secondName.isNullOrEmpty() -> return false
            selectedDob < 1 -> return false
        }
        return true
    }

}