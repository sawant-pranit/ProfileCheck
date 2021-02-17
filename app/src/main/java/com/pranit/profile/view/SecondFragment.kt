package com.pranit.profile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.pranit.profile.R
import com.pranit.profile.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.layout_back_button.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment() {

    private val profileModel : ProfileViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle()
        observeLiveData()
        setClickListeners()
    }

    private fun setClickListeners() {
        clBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun observeLiveData() {
        profileModel.updatedProfileData.observe(viewLifecycleOwner){
            etxtFirstName.setText(it.firstName)
            etxtSecondName.setText(it.lastName)
            val ageData = profileModel.calculateAge(it.age)
            txtvAge.text = getString(R.string.message_age, ageData.first, ageData.second, ageData.third)
        }
    }

    private fun setTitle() {
        txtvTitle.text = getString(R.string.title_second_fragment)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment.
         *
         * @return A new instance of fragment SecondFragment.
         */
        @JvmStatic
        fun newInstance() =
            SecondFragment()
    }
}