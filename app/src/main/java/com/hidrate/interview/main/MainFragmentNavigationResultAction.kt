package com.hidrate.interview.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

const val MAIN_FRAGMENT_RESULT_REQUEST_KEY = "MainActivity"
const val MAIN_RESULT_ACTION_FRAGMENT_KEY = "FragmentKey"

sealed class MainFragmentNavigationResultAction : Parcelable {

    @Parcelize
    object NavigateToSecondFragment : MainFragmentNavigationResultAction(), Parcelable

}